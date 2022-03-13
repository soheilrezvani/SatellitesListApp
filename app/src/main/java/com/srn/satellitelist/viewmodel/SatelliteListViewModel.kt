package com.srn.satellitelist.viewmodel

import androidx.lifecycle.*
import com.srn.satellitelist.data.SatelliteListRepository
import com.srn.satellitelist.entity.Satellite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SoheilR .
 */
@HiltViewModel
class SatelliteListViewModel @Inject internal constructor(
    satelliteListRepository: SatelliteListRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val stateFlow: MutableStateFlow<String> = MutableStateFlow(
        savedStateHandle.get(FILTER_SAVED_STATE_KEY) ?: NO_FILTER
    )

    val satellites: LiveData<List<Satellite>> = stateFlow.flatMapLatest { hp ->
        when {
            isSearchForStatus(hp) -> satelliteListRepository.getSatelliteStatus(hp)
            hp.isEmpty() -> satelliteListRepository.getSatelliteList()
            else -> satelliteListRepository.getSatelliteListByName(hp)

        }

    }.asLiveData()

    init {
        viewModelScope.launch {
            stateFlow.collect { hp ->
                savedStateHandle.set(FILTER_SAVED_STATE_KEY, hp)
            }
        }
    }
    fun loadData(hp: String) {
        stateFlow.value = hp
    }

    private fun isSearchForStatus(hp: String): Boolean {
        // this search could be more Optimum than this
        var result: Boolean = when {
            hp.compareTo("ACTIVE", true) == 0 -> true
            hp.compareTo("ACT", true) == 0 -> true
            hp.compareTo("PASSIVE", true) == 0 -> true
            hp.compareTo("PAS", true) == 0 -> true
            else -> false
        }
        return result
    }

    companion object {
        private const val NO_FILTER = ""
        private const val NAME_FILTER = "NAME_FILTER"
        private const val STATUS_FILTER = "STATUS"

        private const val FILTER_SAVED_STATE_KEY = "FILTER_SAVED_STATE_KEY"
    }
}
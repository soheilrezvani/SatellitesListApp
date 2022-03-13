package com.srn.satellitelist.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.srn.satellitelist.data.SatelliteDetailRepository
import com.srn.satellitelist.data.SatellitePositionRepository
import com.srn.satellitelist.entity.Satellite
import com.srn.satellitelist.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by SoheilR .
 */


@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val satelliteDetailRepository: SatelliteDetailRepository,
    private val satellitePositionRepository: SatellitePositionRepository,
) : ViewModel() {

    var satId: Int = 0
    var satelliteName: String = ""

    var detailData = satelliteDetailRepository.getSatelliteDetail(satId).asLiveData()
    var positionData = satellitePositionRepository.getSatellitePosition().asLiveData()

    fun loadData(data: Satellite, function: () -> Unit) {
        satId = data.id
        satelliteName = data.name
        detailData = satelliteDetailRepository.getSatelliteDetail(satId).asLiveData()
        positionData = satellitePositionRepository.getSatellitePosition().asLiveData()
        updatePosition()
    }

    private fun satelliteName(): String {
        return satelliteName
    }

    private fun updatePosition(): Job {

        return viewModelScope.launch {
            while (true) {

//                positionRepository.getPositionById("2").collect {}

                delay(Constants.UPDATE_POSITION_INTERVAL_RATE)
            }
        }

    }

    companion object {
        private const val SAT_ID_SAVED_STATE_KEY = "satId"
    }
}

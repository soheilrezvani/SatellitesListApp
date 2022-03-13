package com.srn.satellitelist.ui.satellitedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.srn.satellitelist.R
import com.srn.satellitelist.databinding.SatelliteDetailFragmentBinding
import com.srn.satellitelist.entity.Satellite
import com.srn.satellitelist.entity.SatelliteDetail
import com.srn.satellitelist.utils.Constants
import com.srn.satellitelist.viewmodel.SatelliteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by SoheilR .
 */
@AndroidEntryPoint
class SatelliteDetailsFragment : Fragment() {

    private val satelliteDetailViewModel: SatelliteDetailViewModel by viewModels()
    private lateinit var binding: SatelliteDetailFragmentBinding
    private val args: SatelliteDetailsFragmentArgs by navArgs()
    private var updatePositionJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate<SatelliteDetailFragmentBinding>(
            inflater,
            R.layout.satellite_detail_fragment,
            container,
            false
        ).apply {
            viewModel = satelliteDetailViewModel
            lifecycleOwner = viewLifecycleOwner

        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.card
        getDetailData(data)

    }

    private fun getDetailData(data: Satellite) {
        lifecycleScope.launch {
            satelliteDetailViewModel.loadData(data) {
            }
        }
        updateUi()
    }
    private fun updateUi(){
        lifecycleScope.launch {
            while (true) {
                //do your network request here
//                positionRepository.getPositionById("2").collect {}
                val currentTime = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                binding.lastPositionText.text = currentTime
                delay(Constants.UPDATE_POSITION_INTERVAL_RATE)
            }
        }
    }

    fun interface Callback {
        fun add(satellite: SatelliteDetail)
    }
}
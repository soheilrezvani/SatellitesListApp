package com.srn.satellitelist.ui.satellitelist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.srn.satellitelist.databinding.SatelliteFragmentBinding
import com.srn.satellitelist.ui.adapter.SatelliteAdapter
import com.srn.satellitelist.viewmodel.SatelliteListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

/**
 * Created by SoheilR .
 */

@AndroidEntryPoint
class SatelliteListFragment : Fragment() {

    private val viewModel: SatelliteListViewModel by viewModels()
    private lateinit var binding: SatelliteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SatelliteFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = SatelliteAdapter()
        binding.satList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        initSearch()
        loadNoFilterData()
        return binding.root
    }

    private fun initSearch() {
        binding.editSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                GlobalScope.launch {
                    delay(1500)
                    withContext(Dispatchers.Main) {
                        binding.editSearch.text.toString().apply {
                            updateData(binding.editSearch.text.toString())
                        }
                    }
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        binding.satList.addItemDecoration(DividerItemDecoration(context,
            LinearLayoutManager.VERTICAL))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun subscribeUi(adapter: SatelliteAdapter) {
        viewModel.satellites.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
    private fun loadNoFilterData(){
        with(viewModel) {
            loadData("")
        }
    }
    private fun updateData(hp: String) {
        with(viewModel) {
            loadData(hp)
        }
    }
}


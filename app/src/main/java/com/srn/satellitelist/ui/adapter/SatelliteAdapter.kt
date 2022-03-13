package com.srn.satellitelist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.srn.satellitelist.databinding.ListItemSatBinding
import com.srn.satellitelist.entity.Satellite
import com.srn.satellitelist.ui.satellitelist.SatelliteListFragmentDirections

/**
 * Created by SoheilR .
 */
class SatelliteAdapter : ListAdapter<Satellite, RecyclerView.ViewHolder>(SatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SatelliteViewHolder(
            ListItemSatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val satellite = getItem(position)
        (holder as SatelliteViewHolder).bind(satellite)
    }

    class SatelliteViewHolder(
        private val binding: ListItemSatBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.sat?.let { satellite ->
                    navigateToDetail(satellite, it)
                }
            }
        }


        private fun navigateToDetail(
            satellite: Satellite,
            view: View,
        ) {
            val direction =
                SatelliteListFragmentDirections.actionSatelliteFragmentToSatelliteDetailsFragment(
                    satellite
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Satellite) {
            binding.apply {
                sat = item
                executePendingBindings()
            }
        }
    }
}

private class SatDiffCallback : DiffUtil.ItemCallback<Satellite>() {

    override fun areItemsTheSame(oldItem: Satellite, newItem: Satellite): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Satellite, newItem: Satellite): Boolean {
        return oldItem == newItem
    }
}
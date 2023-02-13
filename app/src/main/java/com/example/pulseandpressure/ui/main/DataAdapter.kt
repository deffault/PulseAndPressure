package com.example.pulseandpressure.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pulseandpressure.databinding.ItemDataBinding
import com.example.pulseandpressure.domain.MainData

class DataAdapter : ListAdapter<MainData, DataViewHolder>(DiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)

        return DataViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }

    private class DiffUtils : DiffUtil.ItemCallback<MainData>() {
        override fun areItemsTheSame(oldItem: MainData, newItem: MainData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MainData, newItem: MainData): Boolean {
            return oldItem == newItem
        }
    }
}
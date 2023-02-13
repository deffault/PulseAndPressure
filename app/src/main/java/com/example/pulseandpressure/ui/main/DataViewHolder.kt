package com.example.pulseandpressure.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.pulseandpressure.R
import com.example.pulseandpressure.databinding.ItemDataBinding
import com.example.pulseandpressure.domain.MainData

class DataViewHolder(
    private val binding: ItemDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainData) {
        with(binding) {
            val dateTime = "${item.date} ${item.time}"
            tvDateTime.text = dateTime

            tvTopPressure.text = item.topPressure.toString()
            tvBottomPressure.text = item.bottomPressure.toString()
            tvPulse.text = item.pulse.toString()

            setupBackground(item.topPressure)
        }
    }

    private fun setupBackground(topPressure: Long) {
        when(topPressure) {
            in 110 .. 130 -> binding.dataLayout.setBackgroundResource(R.drawable.good_background)
            in 90 .. 110, in 130 .. 140 -> binding.dataLayout.setBackgroundResource(R.drawable.warning_bacground)
            else -> binding.dataLayout.setBackgroundResource(R.drawable.bad_background)
        }
    }
}
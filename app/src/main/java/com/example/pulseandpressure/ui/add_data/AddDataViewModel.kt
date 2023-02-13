package com.example.pulseandpressure.ui.add_data

import androidx.lifecycle.ViewModel
import com.example.pulseandpressure.data.Repository
import com.example.pulseandpressure.domain.MainData

class AddDataViewModel(
    private val repository: Repository
) : ViewModel() {
    val flow = repository.addDateFlow

    fun addData(data: MainData) {
        repository.insert(data)
    }
}
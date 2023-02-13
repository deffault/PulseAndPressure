package com.example.pulseandpressure.ui.main

import androidx.lifecycle.ViewModel
import com.example.pulseandpressure.data.Repository

class MainViewModel(
    private val repository: Repository
): ViewModel() {
    val listFlow = repository.listDataFlow

    fun getData() {
        repository.getDataList()
    }
}
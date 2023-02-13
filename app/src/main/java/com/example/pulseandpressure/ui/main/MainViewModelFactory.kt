package com.example.pulseandpressure.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pulseandpressure.data.Repository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(repository = repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}
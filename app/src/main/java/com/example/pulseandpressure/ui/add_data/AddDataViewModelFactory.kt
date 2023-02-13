package com.example.pulseandpressure.ui.add_data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pulseandpressure.data.Repository
import javax.inject.Inject

class AddDataViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == AddDataViewModel::class.java) {
            return AddDataViewModel(repository = repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}
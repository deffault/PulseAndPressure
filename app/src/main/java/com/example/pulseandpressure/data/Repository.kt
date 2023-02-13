package com.example.pulseandpressure.data

import com.example.pulseandpressure.domain.MainData
import com.example.pulseandpressure.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Repository {
    protected val _addDataFlow = MutableStateFlow<String>("")
    val addDateFlow: StateFlow<String> = _addDataFlow

    protected val _listDataFlow = MutableStateFlow<Resource>(Resource.Loading)
    val listDataFlow: StateFlow<Resource> = _listDataFlow

    abstract fun insert(data: MainData)

    abstract fun getDataList()
}
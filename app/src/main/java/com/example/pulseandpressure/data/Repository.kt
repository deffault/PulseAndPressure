package com.example.pulseandpressure.data

import com.example.pulseandpressure.domain.MainData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Repository {
    protected val _flow = MutableStateFlow<String>("")
    val flow: StateFlow<String> = _flow

    abstract fun insert(data: MainData)
}
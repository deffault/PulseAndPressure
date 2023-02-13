package com.example.pulseandpressure.utils

import com.example.pulseandpressure.domain.MainData

sealed class Resource {
    data class Success(val data: List<MainData>) : Resource()
    data class Error(val message: String) : Resource()
    object Loading : Resource()
}
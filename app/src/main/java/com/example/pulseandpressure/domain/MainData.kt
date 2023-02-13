package com.example.pulseandpressure.domain

data class MainData(
    val id: String = "",
    val time: String,
    val topPressure: Long,
    val bottomPressure: Long,
    val pulse: Long,
    val date: String
)

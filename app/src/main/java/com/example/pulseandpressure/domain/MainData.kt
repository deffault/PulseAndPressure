package com.example.pulseandpressure.domain

data class MainData(
    val time: String,
    val topPressure: Int,
    val bottomPressure: Int,
    val pulse: Int,
    val date: String
)

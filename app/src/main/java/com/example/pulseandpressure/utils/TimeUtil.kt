package com.example.pulseandpressure.utils

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun getTime(): String {
    val currentTime = Date(System.currentTimeMillis())
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())

    return sdf.format(currentTime)
}

fun getDate(): String {
    val currentTime = Date(System.currentTimeMillis())
    val sdf = SimpleDateFormat("dd", Locale.getDefault())
    val day = sdf.format(currentTime)
    val month = getMonth(Calendar.getInstance().get(Calendar.MONTH))
    val monthRu = RuMonth.valueOf(month).ru

    return "$day $monthRu"
}

private fun getMonth(month: Int): String {
    return DateFormatSymbols().months[month-1]
}

private enum class RuMonth(val ru: String) {
    January("января"),
    February("февраля"),
    March("марта"),
    April("апреля"),
    May("мая"),
    June("июня"),
    July("июля"),
    August("августа"),
    September("сентября"),
    October("октября"),
    November("ноября"),
    December("декабря");
}
package com.example.pulseandpressure

import android.app.Application
import android.content.Context
import com.example.pulseandpressure.di.AppComponent
import com.example.pulseandpressure.di.DaggerAppComponent

class App : Application() {
    val applicationComponent = DaggerAppComponent.create()
}

val Context.appComponent : AppComponent
    get() = when(this) {
        is App -> applicationComponent
        else -> this.applicationContext.appComponent
    }
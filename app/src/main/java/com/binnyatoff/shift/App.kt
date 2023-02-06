package com.binnyatoff.shift

import android.app.Application
import android.content.Context
import com.binnyatoff.shift.di.AppComponent
import com.binnyatoff.shift.di.DaggerAppComponent

class App:Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent= DaggerAppComponent.builder().build()
    }
}
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
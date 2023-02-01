package com.binnyatoff.shift

import android.app.Application
import com.binnyatoff.shift.di.AppComponent
import com.binnyatoff.shift.di.DaggerAppComponent

class App:Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent= DaggerAppComponent.create()
    }
}
package com.example.gcashinterviewapp

import android.app.Application
import com.example.gcashinterviewapp.di.AppComponent
import com.example.gcashinterviewapp.di.DaggerAppComponent

class GCashApplication : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}
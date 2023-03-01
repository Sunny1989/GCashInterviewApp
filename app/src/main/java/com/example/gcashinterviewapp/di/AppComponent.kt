package com.example.gcashinterviewapp.di


import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [NetworkModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun getWeatherComponent() : WeatherComponent

    fun getMap() : Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }
}
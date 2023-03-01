package com.example.gcashinterviewapp.di
import android.content.Context
import com.example.gcashinterviewapp.db.WeatherDB
import com.example.gcashinterviewapp.repository.WeatherRepository
import com.example.gcashinterviewapp.services.WeathersAPI
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun getWeatherRepository(weathersAPI: WeathersAPI, weatherDB: WeatherDB, context: Context) : WeatherRepository{
        return WeatherRepository(weathersAPI,weatherDB,context)
    }
}
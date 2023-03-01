package com.example.gcashinterviewapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gcashinterviewapp.models.WeatherItem

@Database(entities = [WeatherItem::class], version = 1)
abstract class WeatherDB : RoomDatabase() {
    abstract fun getWeatherDAO(): WeatherDAO
}

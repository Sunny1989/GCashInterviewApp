package com.example.gcashinterviewapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.gcashinterviewapp.models.WeatherItem


@Dao
interface WeatherDAO {

    @Insert(onConflict = REPLACE)
    fun addWeatherDetails(weatherItem: WeatherItem)

    @Query("Select * from WeatherItem order by id desc")
    suspend fun getWeatherDetails(): List<WeatherItem>
}


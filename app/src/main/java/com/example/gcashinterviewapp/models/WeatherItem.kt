package com.example.gcashinterviewapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gcashinterviewapp.utils.convertKelvinToCelsius
import com.example.gcashinterviewapp.utils.convertTimeToDate

@Entity
data class WeatherItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var city: String,
    var country: String,
    var iconId: String?,
    var description: String?,
    var currentTemp : String?,
    var sunriseTime: String?,
    var sunsetTime: String?
){

    var celciusTemp : String?
        get() = currentTemp?.convertKelvinToCelsius()
        set(value) {}

    var sunrise : String?
        get() = sunriseTime?.convertTimeToDate()
        set(value) {}

    var sunset : String?
        get() = sunsetTime?.convertTimeToDate()
        set(value) {}

    /*var currentTemp: String
        get() = currentTemp.convertKelvinToCelcius(currentTemp)
        set(value) {}*/

    /*var sunriseTime: String
        get() = sunriseTime.convertTimeToDate(sunriseTime)
        set(value) {}

    var sunsetTime: String
        get() = sunsetTime.convertTimeToDate(sunsetTime)
        set(value) {}*/

}

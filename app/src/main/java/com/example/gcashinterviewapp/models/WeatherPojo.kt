package com.example.gcashinterviewapp.db

import com.example.gcashinterviewapp.models.Clouds
import com.example.gcashinterviewapp.models.Weather

data class WeatherPojo(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
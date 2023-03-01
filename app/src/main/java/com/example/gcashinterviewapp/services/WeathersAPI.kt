package com.example.gcashinterviewapp.services

import com.example.gcashinterviewapp.db.WeatherPojo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeathersAPI {

    @GET("data/2.5/weather")
    suspend fun getWeatherDetails(@Query("lat") lat : Double,@Query("lon") lon : Double,
        @Query("appid") appid : String) : Response<WeatherPojo>

}
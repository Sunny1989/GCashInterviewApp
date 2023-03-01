package com.example.gcashinterviewapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gcashinterviewapp.db.WeatherDB
import com.example.gcashinterviewapp.models.WeatherItem
import com.example.gcashinterviewapp.services.WeathersAPI
import com.example.gcashinterviewapp.utils.NetworkUtils
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weathersAPI: WeathersAPI,
    private val weatherDB: WeatherDB, private val context: Context
) {

    private val weatherListMutableLiveData = MutableLiveData<Response<List<WeatherItem>>>()
    val weatherListLiveData: LiveData<Response<List<WeatherItem>>>
        get() = weatherListMutableLiveData

    private val weatherMutableLiveData = MutableLiveData<Response<WeatherItem>>()
    val weatherLiveData: LiveData<Response<WeatherItem>>
        get() = weatherMutableLiveData

    suspend fun getWeatherData(lat: Double, lon: Double, apiKey: String) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                val response = weathersAPI.getWeatherDetails(lat, lon, apiKey)
                if (response.isSuccessful && response.body() != null) {
                    val city = response.body()?.name.toString()
                    val country = response.body()?.sys?.country.toString()
                    val currentTemp = response.body()?.main?.temp
                    val sunriseTime = response.body()?.sys?.sunrise
                    val sunsetTime = response.body()?.sys?.sunset
                    val iconId = response.body()?.weather?.get(0)?.icon
                    val description = response.body()?.weather?.get(0)?.description

                    val weatherItem = WeatherItem(
                        0,
                        city,
                        country,
                        iconId,
                        description,
                        currentTemp.toString(),
                        sunriseTime.toString(),
                        sunsetTime.toString()
                    )

                    weatherDB.getWeatherDAO().addWeatherDetails(weatherItem)
                    weatherMutableLiveData.postValue(Response.Success(weatherItem))
                }
            } catch (e: Exception) {
                weatherMutableLiveData.postValue(Response.Error(e.message.toString()))
            }
        }else{
            weatherMutableLiveData.postValue(Response.Error("No Internet Connection!"))
        }
    }

    suspend fun getWeatherDataFromDB() {
        val weatherList = weatherDB.getWeatherDAO().getWeatherDetails()
        if (weatherList.isEmpty()) {
            weatherListMutableLiveData.postValue(Response.Error("Weather List is Empty"))
        } else {
            weatherListMutableLiveData.postValue(Response.Success(weatherList))
        }
    }

}
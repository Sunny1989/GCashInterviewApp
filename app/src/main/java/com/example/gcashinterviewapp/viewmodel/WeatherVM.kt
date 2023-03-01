package com.example.gcashinterviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gcashinterviewapp.models.WeatherItem
import com.example.gcashinterviewapp.repository.Response
import com.example.gcashinterviewapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherVM @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    /*val productsLiveData : LiveData<List<WeatherItem>>
        get() = weatherRepository.productsLiveData*/

    val weatherLiveData : LiveData<Response<WeatherItem>>
        get() = weatherRepository.weatherLiveData

    /*init{
        viewModelScope.launch (Dispatchers.IO){
            //weatherRepository.getWeatherData(lat,lon,apiKey)
        }
    }*/

    fun callWeatherDetails(lat : Double, lon : Double, apiKey : String){
        viewModelScope.launch (Dispatchers.IO){
            weatherRepository.getWeatherData(lat,lon,apiKey)
        }
    }

}
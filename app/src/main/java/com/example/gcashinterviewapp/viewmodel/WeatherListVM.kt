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

class WeatherListVM @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    val weatherListLiveData : LiveData<Response<List<WeatherItem>>>
        get() = weatherRepository.weatherListLiveData

    init{
        viewModelScope.launch (Dispatchers.IO){
            weatherRepository.getWeatherDataFromDB()
        }
    }

}
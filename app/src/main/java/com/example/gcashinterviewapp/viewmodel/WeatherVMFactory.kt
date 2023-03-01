package com.example.gcashinterviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class WeatherVMFactory @Inject constructor(private val weatherVM: WeatherVM) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return weatherVM as T
        }

}

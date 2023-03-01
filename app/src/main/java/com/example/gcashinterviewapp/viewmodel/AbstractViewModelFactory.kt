package com.example.gcashinterviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

//This factory method will work for any type of vide model factory, we just have to write
//abstract method in viewModelModule
class AbstractViewModelFactory @Inject constructor(private val map: Map<Class<*>,
        @JvmSuppressWildcards ViewModel>) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}
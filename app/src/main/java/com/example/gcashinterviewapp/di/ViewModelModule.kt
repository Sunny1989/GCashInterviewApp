package com.example.gcashinterviewapp.di

import androidx.lifecycle.ViewModel
import com.example.gcashinterviewapp.viewmodel.WeatherListVM
import com.example.gcashinterviewapp.viewmodel.WeatherVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(WeatherVM::class)
    @IntoMap
    abstract fun weatherViewModel(weatherVM: WeatherVM): ViewModel

    @Binds
    @ClassKey(WeatherListVM::class)
    @IntoMap
    abstract fun weatherListViewModel(weatherListVM: WeatherListVM): ViewModel
}
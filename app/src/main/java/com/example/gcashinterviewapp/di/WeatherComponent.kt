package com.example.gcashinterviewapp.di
import com.example.daggerretrofitroomexample.di.scopes.ActivityScope
import com.example.gcashinterviewapp.ui.main.CurrentWeatherFragment
import com.example.gcashinterviewapp.ui.main.WeatherListFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [WeatherModule::class])
interface WeatherComponent {
    fun injectWeatherFragment(currentWeatherFragment: CurrentWeatherFragment)
    fun injectWeatherListFragment(weatherListFragment: WeatherListFragment)
}
package com.example.gcashinterviewapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gcashinterviewapp.R

private val TAB_TITLES = arrayOf(
    R.string.tab_current,
    R.string.tab_weather_list
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private  var currentWeatherFragment: CurrentWeatherFragment? = null
    private  var weatherListFragment: WeatherListFragment? = null

    override fun getItem(position: Int): Fragment {
        if(position == 0){
            if(currentWeatherFragment == null){
                currentWeatherFragment = CurrentWeatherFragment.newInstance()
            }
            return currentWeatherFragment as CurrentWeatherFragment
        }else{
            if(weatherListFragment == null){
                weatherListFragment = WeatherListFragment.newInstance()
            }
            return weatherListFragment as WeatherListFragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}
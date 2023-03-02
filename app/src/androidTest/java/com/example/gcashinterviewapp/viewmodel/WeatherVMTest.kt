package com.example.gcashinterviewapp.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.gcashinterviewapp.db.WeatherDB
import com.example.gcashinterviewapp.getOrAwaitValue
import com.example.gcashinterviewapp.repository.WeatherRepository
import com.example.gcashinterviewapp.services.WeathersAPI
import com.example.gcashinterviewapp.utils.Constants
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class WeatherVMTest {

    private lateinit var context: Context
    private lateinit var db: WeatherDB
    private lateinit var weathersAPI: WeathersAPI
    private lateinit var weatherVM: WeatherVM
    private lateinit var weatherRepository: WeatherRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDB::class.java).allowMainThreadQueries()
            .build()
        weathersAPI = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeathersAPI::class.java)
        weatherRepository = WeatherRepository(weathersAPI, db, context)
    }

    @Test
    fun callWeatherDetails() {
        weatherVM = WeatherVM(weatherRepository)
        weatherVM.callWeatherDetails(24.5854,73.7125,"1b5232da0bd907bce4d03449e871be86")
        var result = weatherVM.weatherLiveData.getOrAwaitValue()
        TestCase.assertEquals("Udaipur", result?.data1?.city)
    }

    @After
    fun tearDown(){
        db.close()
    }
}
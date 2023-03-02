package com.example.gcashinterviewapp.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.gcashinterviewapp.db.WeatherDB
import com.example.gcashinterviewapp.getOrAwaitValue
import com.example.gcashinterviewapp.models.WeatherItem
import com.example.gcashinterviewapp.repository.WeatherRepository
import com.example.gcashinterviewapp.services.WeathersAPI
import com.example.gcashinterviewapp.utils.Constants
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class WeatherListVMTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherListVM: WeatherListVM
    private lateinit var context: Context
    private lateinit var db: WeatherDB
    private lateinit var weathersAPI: WeathersAPI
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDB::class.java).allowMainThreadQueries()
            .build()
        weathersAPI = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeathersAPI::class.java)
        weatherRepository = WeatherRepository(weathersAPI, db, context)
        addInDB()
    }

    private fun addInDB() {
        val weatherItem = WeatherItem(
            0, "Udaipur", "In",
            "10d.png", "clear sky", "293.99", "1677720452", "1677762459"
        )
        db.getWeatherDAO().addWeatherDetails(weatherItem)
    }

    @Test
    fun getWeatherListLiveData() {
        weatherListVM = WeatherListVM(weatherRepository)
        var result = weatherListVM.weatherListLiveData.getOrAwaitValue()
        assertEquals(1, result?.data1?.size)
        assertEquals("Udaipur", result?.data1?.get(0)?.city)
    }

    @After
    fun tearDown() {
        db.close()
    }
}
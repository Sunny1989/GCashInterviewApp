package com.example.gcashinterviewapp.di
import android.content.Context
import androidx.room.Room
import com.example.gcashinterviewapp.db.WeatherDB
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun getDB(context: Context) : WeatherDB {
        return Room.databaseBuilder(context, WeatherDB::class.java, "WeatherDB").build()
    }
}
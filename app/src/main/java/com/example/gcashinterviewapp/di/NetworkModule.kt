package com.example.gcashinterviewapp.di
import com.example.gcashinterviewapp.services.WeathersAPI
import com.example.gcashinterviewapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit{
        return Retrofit.Builder().
        baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getProductsApi(retrofit: Retrofit) : WeathersAPI {
        return retrofit.create(WeathersAPI::class.java)
    }

}
package com.hianuy.weather.di

import com.google.gson.Gson
import com.hianuy.weather.BuildConfig
import com.hianuy.weather.api.ApiService
import com.hianuy.weather.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl() =  BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun providesRetrofitInstance(BASE_URl: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)


}
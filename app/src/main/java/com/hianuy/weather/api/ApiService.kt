package com.hianuy.weather.api

import com.hianuy.weather.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("weather/{city}")
    suspend fun getWeather(
        @Path("city") city: String
    ): Response<Weather>
}
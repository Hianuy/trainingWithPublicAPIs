package com.hianuy.weather.repository

import com.hianuy.weather.api.ApiService
import javax.inject.Inject


class WeatherRepository
@Inject
 constructor(private val apiService: ApiService){
     suspend fun getWeather() = apiService.getWeather("Fortaleza")
}
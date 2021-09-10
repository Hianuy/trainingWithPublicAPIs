package com.hianuy.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hianuy.weather.model.Weather
import com.hianuy.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val repository: WeatherRepository): ViewModel(){
    private val _response = MutableLiveData<Weather>()
    val weatherResponse: LiveData<Weather>
    get() = _response

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
    get() = _errorMessage


    private val _loading = MutableLiveData<Boolean>()
    val loading :  LiveData<Boolean>
    get() = _loading



    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        _loading.postValue(true)
        repository.getWeather().let { response->
            if (response.isSuccessful){
                _response.postValue(response.body())
                _loading.postValue(false)
            }else{
                _errorMessage.postValue(response.message())
                _loading.postValue(false)
            }

        }
    }

}


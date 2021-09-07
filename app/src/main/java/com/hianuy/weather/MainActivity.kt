package com.hianuy.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.hianuy.weather.databinding.ActivityMainBinding
import com.hianuy.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        viewModel.weatherResponse.observe(this, { weather ->
            binding.apply {
                txtCity.text = "Fortaleza"
                txtTemperature.text = weather.temperature
                txtDescription.text = weather.description
                txtWind.text = weather.wind
                val forecast = weather.forecast
                binding.txtTomorrowForecast.text =
                    "${forecast[0].temperature}/ ${forecast[0].wind} "
                binding.txtAfterTomorrowForecast.text =
                    "${forecast[1].temperature}/ ${forecast[1].wind} "


            }


        })
    }


}
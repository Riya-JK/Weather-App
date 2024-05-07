package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.data.mappers.toWeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(
                lat = lat,
                long = long
                ).toWeatherInfo()
            )
        }catch (e : java.lang.Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}
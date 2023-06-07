package com.example.compose_material3_test.api.forecast

import com.example.compose_material3_test.BuildConfig
import com.example.compose_material3_test.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastAPI {

    @GET("v1/forecast.json")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("days") days: Int = 10
    ): ForecastResponse

}
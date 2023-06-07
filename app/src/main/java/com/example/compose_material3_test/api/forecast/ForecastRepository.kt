package com.example.compose_material3_test.api.forecast

import com.example.compose_material3_test.model.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    fun getForecast(
        query: String
    ) : Flow<ForecastResponse>

}
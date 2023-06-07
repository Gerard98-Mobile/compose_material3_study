package com.example.compose_material3_test.api.forecast

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val api : ForecastAPI
) : ForecastRepository {

    override fun getForecast(query: String) = flow {
        emit(api.getForecast(query))
    }

}
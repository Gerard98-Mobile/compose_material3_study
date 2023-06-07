package com.example.compose_material3_test.ui.search

import com.example.compose_material3_test.model.ForecastResponse

data class SearchUiState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val data: ForecastResponse? = null
)
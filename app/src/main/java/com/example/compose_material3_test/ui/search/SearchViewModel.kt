package com.example.compose_material3_test.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_material3_test.api.forecast.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository : ForecastRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    val query: String

    init {
        query = savedStateHandle.get<String>("query").orEmpty()
        fetchForecast()
    }

    private fun fetchForecast() = viewModelScope.launch {
        _uiState.value = SearchUiState(loading = true)
        repository.getForecast(query)
            .flowOn(Dispatchers.IO)
            .catch {
                _uiState.value = SearchUiState(error = it)
                it.printStackTrace()
            }
            .collect { _uiState.value = SearchUiState(data = it) }
    }

}
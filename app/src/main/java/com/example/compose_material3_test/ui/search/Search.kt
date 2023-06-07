package com.example.compose_material3_test.ui.search

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_material3_test.R
import com.example.compose_material3_test.components.ColumnCentered
import com.example.compose_material3_test.components.GifImage
import com.example.compose_material3_test.components.SpacerW
import com.example.compose_material3_test.components.StickyHeader
import com.example.compose_material3_test.components.image.UrlImage
import com.example.compose_material3_test.components.loading.LoadingDots
import com.example.compose_material3_test.model.ForecastDay
import com.example.compose_material3_test.model.ForecastDays
import com.example.compose_material3_test.model.ForecastHour
import com.example.compose_material3_test.model.ForecastResponse
import com.example.compose_material3_test.ui.theme.BackIcon
import com.example.compose_material3_test.ui.theme.GreyText
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import kotlin.text.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    onBack: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SearchToolbar(scrollBehavior, viewModel.query, onBack) },
        content = { innerPadding -> 
            val value = state.value
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {
                when  {
                    value.loading -> Loading()
                    value.error != null -> Error(value.error)
                    value.data != null -> SearchContent(value.data)
                }
            }
        }
    )
}

@Composable
private fun Loading() {
    ColumnCentered(
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingDots()
    }
}

@Composable
private fun Error(throwable: Throwable) {
    ColumnCentered(Modifier.fillMaxWidth()) {
        Text(text = "Error")

        Text(text = "${throwable.message}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        GifImage(
            drawableResId = R.drawable.kitty,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchToolbar(
    scrollBehavior: TopAppBarScrollBehavior,
    query: String,
    onBack: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackIcon {
                onBack()
            }
        },
        title = {
            Text(
                query,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchContent(
    data: ForecastResponse
) {
    val grouped = data.forecast.forecastDay?.groupBy { it.hours }
    
    LazyColumn {
        data.forecast.forecastDay?.forEach {
            stickyHeader {
                ForecastDayHeader(it)
            }
            items(it.hours) {
                ForecastHour(it)
            }
        }
    }
}

@Composable
private fun ForecastDayHeader(day: ForecastDay) = StickyHeader {
    val weekDay = day.dateTime.dayOfWeek().asText

    Row {
        Text(
            text = weekDay,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = day.date,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
private fun ForecastHour(data: ForecastHour) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, top = 3.dp, bottom = 3.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = data.dateTime.toString("HH:mm") ?: "Unknown",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            
            SpacerW(10.dp)

            ColumnCentered {
                data.condition?.iconUrl?.let {
                    val uri = Uri.parse("https://${it.removePrefix("//")}")
                    UrlImage(
                        uri = uri,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

            SpacerW(10.dp)

            Column {
                Row {
                    Text(
                        text = data.tempC?.toString() ?: "Unknown",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "°C",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = data.condition?.text ?: "",
                    style = MaterialTheme.typography.bodySmall.copy(color = GreyText),

                )
            }
        }
    }
}
package com.example.compose_material3_test.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.io.Serializable

data class ForecastResponse(
    @SerializedName("location")
    val location: Location,
    @SerializedName("forecast")
    val forecast: ForecastDays
) : Serializable

data class Location(
    @SerializedName("lat")
    val latitude: Double? = null,
    @SerializedName("lon")
    val longitude: Double? = null
) : Serializable

data class ForecastDays(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>? = null,
) : Serializable

data class ForecastDay(
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    private val dateEpoch: Long,
    @SerializedName("hour")
    val hours: List<ForecastHour> = emptyList(),
    @SerializedName("time_epoch")
    val startTime: Long
) : Serializable {
    val dateTime: DateTime
        get() = DateTime(dateEpoch * 1000)
}

data class ForecastHour(
    @SerializedName("time_epoch")
    private val timeEpoch: Long,
    @SerializedName("time")
    val time: String? = null,
    @SerializedName("temp_c")
    val tempC: Double? = null,
    @SerializedName("temp_f")
    val tempF: Double? = null,
    @SerializedName("condition")
    val condition: ForecastHourCondition? = null,
) : Serializable {
    val dateTime: DateTime
        get() = DateTime(timeEpoch * 1000)
}

data class ForecastHourCondition(
    @SerializedName("icon")
    val iconUrl: String? = null,
    @SerializedName("text")
    val text: String? = null
) : Serializable

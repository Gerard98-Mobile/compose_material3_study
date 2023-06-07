package com.example.compose_material3_test.di.modules

import com.example.compose_material3_test.api.forecast.ForecastAPI
import com.example.compose_material3_test.api.forecast.ForecastRepository
import com.example.compose_material3_test.api.forecast.ForecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.weatherapi.com/"

    private val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideRetrofit() : Retrofit {
        return retrofit
    }

    @Provides
    fun provideForecastApi(retrofit: Retrofit) : ForecastAPI {
        return retrofit.create(ForecastAPI::class.java)
    }

    @Provides
    fun provideForecastRepository(api : ForecastAPI) : ForecastRepository {
        return ForecastRepositoryImpl(api)
    }

}
package com.ayeminoo.tsuka.data.api

import com.ayeminoo.tsuka.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitProvider(
    private val apiUrl: String,
    private val appId: String,
    private val json : Json,
    private val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
) {

    fun provide(): Retrofit {
        val client = OkHttpClient.Builder()
            .setLogger(logLevel)
            .setAppId(appId)
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .baseUrl(apiUrl)
            .build()
    }

    private fun OkHttpClient.Builder.setAppId(appId: String) = addInterceptor { chain ->
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val newUrl = originalUrl
            .newBuilder()
            .addQueryParameter("app_id", appId)
            .build()
        val newRequest = originalRequest.newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }

    private fun OkHttpClient.Builder.setLogger(
        logLevel: HttpLoggingInterceptor.Level
    ): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = logLevel
                }
            )
        }
        return this
    }

}


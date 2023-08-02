package com.ayeminoo.tsuka.di

import com.ayeminoo.tsuka.BuildConfig
import com.ayeminoo.tsuka.data.api.RetrofitProvider
import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }


    @Singleton
    @Provides
    fun provideRetrofit(json: Json): OpenExchangeApiService =
        RetrofitProvider(
            apiUrl = "https://openexchangerates.org/",
            appId = BuildConfig.APP_ID,
            json
        )
            .provide()
            .create(OpenExchangeApiService::class.java)


}
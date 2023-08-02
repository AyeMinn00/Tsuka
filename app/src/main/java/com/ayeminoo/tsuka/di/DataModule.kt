package com.ayeminoo.tsuka.di

import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import com.ayeminoo.tsuka.data.CurrencyDataSource
import com.ayeminoo.tsuka.data.RemoteCurrencyDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOpenExchangeDataSource(apiService: OpenExchangeApiService): CurrencyDataSource =
        RemoteCurrencyDataSource(apiService)

}
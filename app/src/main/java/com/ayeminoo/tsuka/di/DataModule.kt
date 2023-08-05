package com.ayeminoo.tsuka.di

import com.ayeminoo.tsuka.data.api.DefaultRemoteDataSource
import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import com.ayeminoo.tsuka.data.local.CurrencyDao
import com.ayeminoo.tsuka.data.local.DefaultLocalDataSource
import com.ayeminoo.tsuka.domain.LocalDataSource
import com.ayeminoo.tsuka.domain.RemoteDataSource
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
    fun provideRemoteDataSource(apiService: OpenExchangeApiService): RemoteDataSource =
        DefaultRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: CurrencyDao): LocalDataSource = DefaultLocalDataSource(dao)


}
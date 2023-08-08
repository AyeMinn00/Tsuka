package com.ayeminoo.tsuka.di

import android.content.Context
import com.ayeminoo.tsuka.data.api.DefaultRemoteDataSource
import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import com.ayeminoo.tsuka.data.local.datastore.PrefStore
import com.ayeminoo.tsuka.data.local.db.CurrencyDao
import com.ayeminoo.tsuka.data.local.db.DefaultLocalDataSource
import com.ayeminoo.tsuka.domain.LocalDataSource
import com.ayeminoo.tsuka.domain.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = PrefStore(context)

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

}
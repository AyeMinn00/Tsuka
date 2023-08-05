package com.ayeminoo.tsuka.di

import com.ayeminoo.tsuka.data.CurrencyRepositoryImpl
import com.ayeminoo.tsuka.domain.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindRepo(repo: CurrencyRepositoryImpl): CurrencyRepository

}
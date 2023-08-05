package com.ayeminoo.tsuka.di

import android.content.Context
import androidx.room.Room
import com.ayeminoo.tsuka.data.local.CurrencyDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CurrencyDb::class.java, "tsuka.db").build()

    @Singleton
    @Provides
    fun provideDao(db: CurrencyDb) = db.currencyDao()

}
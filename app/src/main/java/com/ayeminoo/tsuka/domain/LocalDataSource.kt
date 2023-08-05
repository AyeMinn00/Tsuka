package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.data.local.db.CurrencyEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllCurrency(): Flow<List<CurrencyEntity>>

    suspend fun insertCurrency(data: List<CurrencyEntity>)

}
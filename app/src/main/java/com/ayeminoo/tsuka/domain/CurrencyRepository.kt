package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    val currencies: Flow<List<Currency>>

    suspend fun refreshCurrencyFromNetwork()

    fun getBaseCurrency(): Flow<String>

    suspend fun setBaseCurrency(cur: String)

}
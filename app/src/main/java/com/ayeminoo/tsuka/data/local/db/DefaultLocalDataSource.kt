package com.ayeminoo.tsuka.data.local.db

import com.ayeminoo.tsuka.domain.LocalDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultLocalDataSource @Inject constructor(
    private val currencyDao: CurrencyDao
) : LocalDataSource {
    override fun getAllCurrency(): Flow<List<CurrencyEntity>> {
        return currencyDao.getAllCurrency()
    }

    override suspend fun insertCurrency(data: List<CurrencyEntity>) {
        currencyDao.insertCurrency(data)
    }


}
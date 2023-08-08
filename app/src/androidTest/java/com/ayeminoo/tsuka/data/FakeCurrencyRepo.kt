package com.ayeminoo.tsuka.data

import com.ayeminoo.tsuka.DataProvider
import com.ayeminoo.tsuka.di.MainDispatcher
import com.ayeminoo.tsuka.domain.CurrencyRepository
import com.ayeminoo.tsuka.models.Currency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeCurrencyRepo(
     private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : CurrencyRepository {

    override val currencies: Flow<List<Currency>>
        get() = flow {
            emit(DataProvider.sampleCurrencies)
        }.flowOn(dispatcher)

    override suspend fun refreshCurrencyFromNetwork() {
    }

    override fun getBaseCurrency(): Flow<String> {
        return flow {
            emit(DataProvider.baseCurrency)
        }.flowOn(dispatcher)
    }

    override suspend fun setBaseCurrency(cur: String) {

    }

    override fun getLastUpdatedDateTime(): Flow<String> {
        return flow {
            emit(DataProvider.LAST_UPDATE)
        }.flowOn(dispatcher)
    }


}
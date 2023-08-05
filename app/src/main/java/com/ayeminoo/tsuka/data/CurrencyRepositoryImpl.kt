package com.ayeminoo.tsuka.data

import com.ayeminoo.tsuka.data.api.model.DataState.Error
import com.ayeminoo.tsuka.data.api.model.DataState.Success
import com.ayeminoo.tsuka.data.local.CurrencyEntity
import com.ayeminoo.tsuka.data.local.toDomainModel
import com.ayeminoo.tsuka.di.IoDispatcher
import com.ayeminoo.tsuka.domain.CurrencyRepository
import com.ayeminoo.tsuka.domain.LocalDataSource
import com.ayeminoo.tsuka.domain.RemoteDataSource
import com.ayeminoo.tsuka.models.Currency
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

class CurrencyRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CurrencyRepository {

    override val currencies: Flow<List<Currency>>
        get() = localDataSource
            .getAllCurrency()
            .map { list -> list.toDomainModel() }
            .flowOn(dispatcher)


    override suspend fun refreshCurrencyFromNetwork() {
        withContext(dispatcher) {
            when (val response = remoteDataSource.getLatestCurrency()) {
                is Success -> {
                    localDataSource.insertCurrency(
                        response.data.rates
//                            .filter {
//                                (it.key == "JPY" || it.key == "MMK" || it.key == "THB")
//                            }
                            .map { map ->
                                CurrencyEntity(
                                    map.key,
                                    map.value.toBigDecimal().toPlainString()
                                )
                            })
                }

                is Error -> {
                    Timber.e(response.error)
                }
            }
        }
    }


}
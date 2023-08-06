package com.ayeminoo.tsuka.data

import com.ayeminoo.tsuka.constants.Constants
import com.ayeminoo.tsuka.data.api.model.DataState.Error
import com.ayeminoo.tsuka.data.api.model.DataState.Success
import com.ayeminoo.tsuka.data.local.datastore.PrefStore
import com.ayeminoo.tsuka.data.local.db.CurrencyEntity
import com.ayeminoo.tsuka.data.local.db.toDomainModel
import com.ayeminoo.tsuka.di.IoDispatcher
import com.ayeminoo.tsuka.domain.CurrencyRepository
import com.ayeminoo.tsuka.domain.LocalDataSource
import com.ayeminoo.tsuka.domain.RemoteDataSource
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.utils.DateFormatter
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
    private val prefDataSource: PrefStore,
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
                    prefDataSource.saveUpdatedTimeStamp(
                        DateFormatter().current()
                    )
                    localDataSource.insertCurrency(
                        response.data.rates.toMutableMap()
                            .apply { put(Constants.DEFAULT_BASE_CURRENCY, 1.toDouble()) }
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

    override fun getBaseCurrency(): Flow<String> {
        return prefDataSource.getBaseCurrency
    }

    override suspend fun setBaseCurrency(cur: String) {
        prefDataSource.saveBaseCurrency(cur)
    }

    override fun getLastUpdatedDateTime(): Flow<String> {
        return prefDataSource.getUpdatedTimeStamp
    }

}
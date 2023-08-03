package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.data.CurrencyDataSource
import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson
import com.ayeminoo.tsuka.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class ConvertCurrencyUseCase @Inject constructor(
    private val dataSource: CurrencyDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : ApiUseCase<Unit, LatestJson>(dispatcher) {

    override suspend fun execute(parameters: Unit): DataState<LatestJson> {
        return dataSource.getLatestCurrency()
    }

}
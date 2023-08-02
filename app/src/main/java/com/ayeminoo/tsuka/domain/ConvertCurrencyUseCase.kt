package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.data.CurrencyDataSource
import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ConvertCurrencyUseCase @Inject constructor(
    private val dataSource: CurrencyDataSource,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ApiUseCase<Unit, LatestJson>(dispatcher) {
    override suspend fun execute(parameters: Unit): DataState<LatestJson> {
        return dataSource.getLatestCurrency()
    }


}
package com.ayeminoo.tsuka.data

import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson
import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import com.ayeminoo.tsuka.data.api.safeApiCall
import com.ayeminoo.tsuka.domain.CurrencyDataSource
import javax.inject.Inject

class RemoteCurrencyDataSource @Inject constructor(
    private val openExchangeApiService: OpenExchangeApiService
) : CurrencyDataSource {
    override suspend fun getLatestCurrency(): DataState<LatestJson> {
        return safeApiCall { openExchangeApiService.fetchLatest() }
    }


}
package com.ayeminoo.tsuka.data.api

import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson
import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import com.ayeminoo.tsuka.domain.RemoteDataSource
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val openExchangeApiService: OpenExchangeApiService
) : RemoteDataSource {
    override suspend fun getLatestCurrency(): DataState<LatestJson> {
        return safeApiCall { openExchangeApiService.fetchLatest() }
    }


}
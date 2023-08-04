package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson

interface CurrencyDataSource {

    suspend fun getLatestCurrency(): DataState<LatestJson>

}
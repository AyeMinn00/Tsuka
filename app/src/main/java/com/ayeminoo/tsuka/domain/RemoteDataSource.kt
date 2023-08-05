package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson

interface RemoteDataSource {

    suspend fun getLatestCurrency(): DataState<LatestJson>

}
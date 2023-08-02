package com.ayeminoo.tsuka.data.api.services

import com.ayeminoo.tsuka.data.api.model.LatestJson
import retrofit2.Response
import retrofit2.http.GET

interface OpenExchangeApiService {

    @GET("api/latest.json")
    suspend fun fetchLatest(): Response<LatestJson>

}
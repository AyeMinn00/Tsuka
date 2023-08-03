package com.ayeminoo.tsuka.data.api

import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.DataState.Error
import com.ayeminoo.tsuka.data.api.model.DataState.Success
import retrofit2.Response

inline fun <T> safeApiCall(apiCall: () -> Response<T>): DataState<T> {

    try {
        val response = apiCall()
        val code = response.code()
        if (response.isSuccessful) {
            val body = response.body()
            return if (body == null) {
                Error(
                    statusCode = code,
                    error = "",
                )
            } else {
                Success(
                    statusCode = code,
                    data = body
                )
            }
        }
        return Error(
            statusCode = code,
            error = response.message(),
        )
    } catch (e: Exception) {
        return Error(
            statusCode = 105,
            e.message ?: "Unknown Error",
        )
    }

}
package com.ayeminoo.tsuka.data.api

import com.ayeminoo.tsuka.constants.Constants.UNKNOWN_STATUS_CODE
import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.DataState.Error
import com.ayeminoo.tsuka.data.api.model.DataState.Success
import java.io.IOException
import java.net.UnknownHostException
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
    } catch (hostExce: UnknownHostException) {
        return Error(
            statusCode = UNKNOWN_STATUS_CODE,
            "Unknown Host Error",
        )
    } catch (ie: IOException) {
        return Error(
            statusCode = UNKNOWN_STATUS_CODE,
            ie.message ?: "Json Parsing Error",
        )
    } catch (e: Exception) {
        return Error(
            statusCode = UNKNOWN_STATUS_CODE,
            e.message ?: "Unknown Error",
        )
    }

}
package com.ayeminoo.tsuka.data.api.model

import okhttp3.ResponseBody

sealed class DataState<out R> {

    data class Success<out T>(
        val statusCode: Int,
        val data: T
    ) : DataState<T>()

    data class Error(
        val statusCode: Int,
        val error: String,
        val body: ResponseBody?
    ) : DataState<Nothing>()


}
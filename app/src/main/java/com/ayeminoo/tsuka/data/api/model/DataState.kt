package com.ayeminoo.tsuka.data.api.model


sealed class DataState<out R> {

    data class Success<out T>(
        val statusCode: Int,
        val data: T
    ) : DataState<T>()

    data class Error(
        val statusCode: Int,
        val error: String,
    ) : DataState<Nothing>()


}

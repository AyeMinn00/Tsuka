package com.ayeminoo.tsuka.domain

import com.ayeminoo.tsuka.data.api.model.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * P - params
 * R - Response
 */
abstract class ApiUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(parameters: P): DataState<R> {
        return withContext(coroutineDispatcher) {
            execute(parameters)
        }
    }

    protected abstract suspend fun execute(parameters: P): DataState<R>
}
package com.ayeminoo.tsuka.data.api.model

import com.ayeminoo.tsuka.models.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestJson(
    @SerialName("base") val base: String,
    @SerialName("timestamp") val timeStamp: Long,
    @SerialName("rates") val rates: Map<String, Double> = emptyMap(),
)

fun LatestJson.toDomainModel() : List<Currency> = rates.map { Currency(it.key, it.value.toString()) }
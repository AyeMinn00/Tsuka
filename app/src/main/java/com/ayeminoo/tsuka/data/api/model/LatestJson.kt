package com.ayeminoo.tsuka.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestJson(
    @SerialName("base") val base: String,
    @SerialName("timestamp") val timeStamp: Long,
    @SerialName("rates") val rates : Map<String,Float> = emptyMap(),
)
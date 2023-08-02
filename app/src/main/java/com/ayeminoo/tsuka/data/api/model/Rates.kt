package com.ayeminoo.tsuka.data.api.model

data class Rates(
    val base: String,
    val timeStamp: String,
    val rates: Map<String, Float> = emptyMap(),
)
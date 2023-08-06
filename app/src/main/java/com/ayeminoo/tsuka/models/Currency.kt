package com.ayeminoo.tsuka.models

import android.os.Parcelable
import com.ayeminoo.tsuka.data.local.db.CurrencyEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val currencyCode: String,
    val amount: String,
    val isBase: Boolean = false
) : Parcelable


fun List<Currency>.toEntity() = this.map { item ->
    CurrencyEntity(
        countryCode = item.currencyCode, rate = item.amount
    )
}
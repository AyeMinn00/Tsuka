package com.ayeminoo.tsuka.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayeminoo.tsuka.models.Currency

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey val countryCode: String,
    @ColumnInfo(name = "rate") val rate: String
)

fun CurrencyEntity.toVo() = Currency(
    currencyCode = countryCode, amount = rate

)

fun List<CurrencyEntity>.toDomainModel() = map { item -> item.toVo() }
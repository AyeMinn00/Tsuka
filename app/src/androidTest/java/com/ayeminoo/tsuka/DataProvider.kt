package com.ayeminoo.tsuka

import com.ayeminoo.tsuka.constants.Constants
import com.ayeminoo.tsuka.models.Currency

object DataProvider {

    const val LAST_UPDATE = "12/2/95"

    val sampleCurrencies = listOf(
        Currency(currencyCode = "USD", amount = "1"),
        Currency(currencyCode = "MMK", amount = "2001.123")
    )

    val baseCurrency = Constants.DEFAULT_BASE_CURRENCY

}
package com.ayeminoo.tsuka.utils

import com.ayeminoo.tsuka.models.Currency
import java.math.RoundingMode
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyConverterTest {

    private val sampleRates = listOf(
        Currency(currencyCode = "THB", amount = "2"),
        Currency(currencyCode = "JPY", amount = "12")
    )

    private val converter = CurrencyConverter(scale = 4, RoundingMode.HALF_UP)

    @Test
    fun convert_ShouldCalculateCorrectlyAndNotIncludingSelectedCurrency() {
        val amount = "2"
        val baseCurrency = "USD"
        val actual = converter.convert(baseCurrency, amount, sampleRates)
        assertEquals(2 , actual.size)
        assertEquals("4" , actual[0].amount)
        assertEquals("THB" , actual[0].currencyCode)
        assertEquals("24" , actual[1].amount)
        assertEquals("JPY" , actual[1].currencyCode)
    }
}
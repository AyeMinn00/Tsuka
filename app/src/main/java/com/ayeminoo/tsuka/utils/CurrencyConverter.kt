package com.ayeminoo.tsuka.utils

import com.ayeminoo.tsuka.constants.Constants.DEFAULT_BASE_CURRENCY
import com.ayeminoo.tsuka.models.Currency
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverter(
    private val scale: Int,
    private val roundingMode: RoundingMode
) {

    fun convert(baseCurrency: String, amount: String, rates: List<Currency>): List<Currency> {
        val data: Map<String, String> = rates.associateBy({ it.currencyCode }, { it.amount })
        return if (baseCurrency == DEFAULT_BASE_CURRENCY)
            convertForBaseUSD(amount, data)
        else convertForBaseNotUSD(baseCurrency, amount, data)
    }

    private fun convertForBaseUSD(amount: String, data: Map<String, String>): List<Currency> {
        return data
            .filterNot { item -> item.key == DEFAULT_BASE_CURRENCY }
            .map {
                Currency(
                    currencyCode = it.key,
                    amount = Calculator.formatOutput(
                        it.value
                            .toBigDecimal()
                            .multiply(amount.toBigDecimal())
                    )
                )
            }
    }

    private fun convertForBaseNotUSD(
        baseCurrency: String,
        amount: String,
        data: Map<String, String>
    ): List<Currency> {
        val bigAmount = try {
            amount.toBigDecimal()
        } catch (ne: NumberFormatException) {
            return emptyList()
        }
        val fromBigValue = data[baseCurrency]?.toBigDecimal() ?: return emptyList()
        return data
            .filterNot { item -> item.key == baseCurrency }
            .map {
                val bigValue = it.value.toBigDecimal()
                val convertedValue = convertCurrency(
                    bigAmount,
                    fromRate = fromBigValue.toDouble(),
                    toRate = bigValue.toDouble()
                )
                Currency(
                    currencyCode = it.key,
                    amount = Calculator.formatOutput(convertedValue)
                )
            }
    }


    private fun convertCurrency(amount: BigDecimal, fromRate: Double, toRate: Double): BigDecimal {
        val valueInDollars = convertAnyCurrencyToUSD(amount, fromRate)
        return convertUSDToAnyCurrency(valueInDollars, toRate)
    }

    private fun convertAnyCurrencyToUSD(amount: BigDecimal, fromRate: Double): BigDecimal {
        return amount.divide(fromRate.toBigDecimal(), scale, roundingMode)
    }

    private fun convertUSDToAnyCurrency(usd: BigDecimal, toRate: Double): BigDecimal {
        return usd.multiply(toRate.toBigDecimal())
    }
}
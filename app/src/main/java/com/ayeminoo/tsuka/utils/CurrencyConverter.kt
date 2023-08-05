package com.ayeminoo.tsuka.utils

import com.ayeminoo.tsuka.constants.Constants
import com.ayeminoo.tsuka.models.Currency
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverter(
    private val scale: Int,
    private val roundingMode: RoundingMode
) {

    fun convert(baseCurrency: String, amount: String, data: Map<String, Float>): List<Currency>? {
        if (baseCurrency == Constants.DEFAULT_BASE_CURRENCY)
            return data.map {
                Currency(
                    it.key,
                    it.value.toString()
                )
            }

        val bigAmount = amount.toBigDecimal()
        val fromBigValue = data[baseCurrency]?.toBigDecimal() ?: return null

        return data.map {
            val bigValue = it.value.toBigDecimal()
            val convertedValue =
                bigValue.divide(fromBigValue, scale, roundingMode).multiply(bigAmount)
            Currency(
                it.key,
                convertedValue.toString()
            )
        }
//            .filter {
//            (it.currencyCode == "JPY" || it.currencyCode == "MMK")
//        }
    }

    fun convert2(baseCurrency: String, amount: String, rates: List<Currency>): List<Currency> {
        val data: Map<String, String> = rates.associateBy({ it.currencyCode }, { it.amount })
        if (baseCurrency == Constants.DEFAULT_BASE_CURRENCY)
            return data.map {
                Currency(
                    it.key,
                    it.value.toBigDecimal().multiply(amount.toBigDecimal()).toPlainString()
                )
            }

        val bigAmount = try {
            amount.toBigDecimal()
        } catch (ne: NumberFormatException) {
            return emptyList()
        }
        val fromBigValue = data[baseCurrency]?.toBigDecimal() ?: return emptyList()
        return data.map {
            val bigValue = it.value.toBigDecimal()
            val convertedValue = convertCurrency(
                bigAmount,
                fromRate = fromBigValue.toDouble(),
                toRate = bigValue.toDouble()
            )
            Currency(
                it.key,
                convertedValue.toPlainString()
            )
        }
    }


    private fun convertCurrency(amount: BigDecimal, fromRate: Double, toRate: Double): BigDecimal {
        val valueInDollars = convertAnyCurrencyToDollar(amount, fromRate)
        return convertDollarToAnyCurrency(valueInDollars, toRate)
    }

    private fun convertAnyCurrencyToDollar(amount: BigDecimal, fromRate: Double): BigDecimal {
        return amount.divide(BigDecimal.valueOf(fromRate), scale, RoundingMode.HALF_UP)
    }

    private fun convertDollarToAnyCurrency(dollarValue: BigDecimal, toRate: Double): BigDecimal {
        return dollarValue.multiply(BigDecimal.valueOf(toRate))
    }
}
package com.ayeminoo.tsuka.utils

import com.ayeminoo.tsuka.constants.Constants.POINT
import java.math.BigDecimal
import java.math.RoundingMode

object Calculator {

    fun removeOnce(valueStr: String): String {
        val value = valueStr.trim()
        if (value.length == 1 || value.isEmpty()) return ""
        val newValue = value.substring(0, value.length - 1)
        if (newValue.last() == '.') return newValue.substring(0, newValue.length - 1)
        return newValue
    }

    fun clear() = "1"

    fun addDecimalPoint(valueStr: String): String {
        val value = valueStr.trim()
        if (value.contains(POINT) || value.isEmpty()) return value
        return value.plus(POINT)
    }

    fun addInput(prevStr: String, newStr: String): String {
        var prevValue = prevStr.trim()
        val newValue = newStr.trim()
        if (prevValue.isNotEmpty() && prevValue.first() == '0' && !prevValue.contains(POINT))
            prevValue = prevValue.substring(1)
        return prevValue.plus(newValue)
    }

    fun nothing(inputStr: String): Boolean {
        val input = inputStr.trim()
        if (input.isBlank() || input == "0") return true
        val pattern = Regex("^0*\\.?0*\$")
        return pattern.matches(input)
    }

    fun formatOutput(value: BigDecimal): String {
        val scaledValue = value.setScale(4, RoundingMode.HALF_UP).toPlainString()
        val dValue = scaledValue.toDouble()
        val formattedValue =
            if (dValue.toInt().toDouble() == dValue)
                dValue.toInt()
            else dValue
        return formattedValue.toString().toBigDecimal().toPlainString()
    }

}











package com.ayeminoo.tsuka.utils

import com.ayeminoo.tsuka.constants.Constants.POINT

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
        if (value.contains(POINT)) return value
        return value.plus(POINT)
    }

    fun addInput(prevStr : String, newStr : String) : String {
        val prevValue = prevStr.trim()
        val newValue = newStr.trim()
        return prevValue.plus(newValue)
    }


}
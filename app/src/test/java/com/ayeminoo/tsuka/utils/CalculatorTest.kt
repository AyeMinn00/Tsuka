package com.ayeminoo.tsuka.utils

import com.ayeminoo.tsuka.constants.Constants.EMPTY_STRING
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun removeOnce_ReturnsEmptyString_WhenInputIsSingleCharacter() {
        val input = "1"
        val actual = Calculator.removeOnce(input)
        val expected = EMPTY_STRING
        assertEquals(expected, actual)
    }

    @Test
    fun removeOnce_ReturnsEmptyString_WhenInputIsEmpty() {
        val input = EMPTY_STRING
        val actual = Calculator.removeOnce(input)
        val expected = EMPTY_STRING
        assertEquals(expected, actual)
    }

    @Test
    fun removeOnce_RemovesLastCharacter_WhenInputIsMultipleCharacters() {
        val input = "123"
        val actual = Calculator.removeOnce(input)
        val expected = "12"
        assertEquals(expected, actual)
    }

    @Test
    fun removeOnce_RemovesLastCharacterIncludingDecimal_WhenOutputEndsWithDecimal() {
        val input = "3.1"
        val actual = Calculator.removeOnce(input)
        val expected = "3"
        assertEquals(expected, actual)
    }

    @Test
    fun addDecimalPoint_ShouldAddPointAtLast() {
        val input = "123"
        val actual = Calculator.addDecimalPoint(input)
        val expected = "123."
        assertEquals(expected, actual)
    }

    @Test
    fun addDecimalPoint_ShouldNotAddPoint_WhenInputHasAlreadyDecimal() {
        val input = "12.3"
        val actual = Calculator.addDecimalPoint(input)
        val expected = "12.3"
        assertEquals(expected, actual)
    }

    @Test
    fun addInput_ShouldAppendInLast() {
        val prevStr = "1"
        val input = "2"
        val actual = Calculator.addInput(prevStr, input)
        val expected = "12"
        assertEquals(expected, actual)
    }

    @Test
    fun addInput_ShouldRemoveFirstCharBeforeAppend_WhenPrevValueStartsWithZero() {
        val prevStr = "0"
        val input = "1"
        val actual = Calculator.addInput(prevStr, input)
        val expected = "1"
        assertEquals(expected, actual)
    }

    @Test
    fun addInput_ShouldNotRemoveFirstZero_WhenPrevValueHasDecimal() {
        val prevStr = "0.1"
        val input = "1"
        val actual = Calculator.addInput(prevStr, input)
        val expected = "0.11"
        assertEquals(expected, actual)
    }


}











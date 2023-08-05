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
    fun addDecimalPoint_ShouldAddPointAtLast(){
        val input = "123"
        val actual = Calculator.addDecimalPoint(input)
        val expected = "123."
        assertEquals(expected, actual)
    }

    @Test
    fun addDecimalPoint_ShouldNotAddPoint_WhenInputHasAlreadyDecimal(){
        val input = "12.3"
        val actual = Calculator.addDecimalPoint(input)
        val expected = "12.3"
        assertEquals(expected, actual)
    }

}











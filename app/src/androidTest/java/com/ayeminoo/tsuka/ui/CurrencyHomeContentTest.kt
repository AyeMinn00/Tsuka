package com.ayeminoo.tsuka.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.ui.composables.CurrencyHomeContent
import org.junit.Rule
import org.junit.Test

class CurrencyHomeContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_verifyContent() {
        val baseCurrency = "USD"
        val data = listOf(
            Currency("THB", "33.33"),
            Currency("JPY", "0.22"),
        )
        val initialAmount = "1.1"
        val lastUpdate = "12-2-1995"
        composeTestRule.setContent {
            CurrencyHomeContent(
                data = data,
                baseCurrency = baseCurrency,
                amount = initialAmount,
                updatedTime = lastUpdate,
                onClickUpdate = { },
                onUpdateAmount = {},
                onClickBaseCurrency = {}
            )
        }
        with(composeTestRule) {
            onNodeWithText("USD").assertIsDisplayed()
            onNodeWithText(initialAmount).assertIsDisplayed()
            data.forEach { currency ->
                onNodeWithText(currency.currencyCode).assertIsDisplayed()
                onNodeWithText(currency.amount).assertIsDisplayed()
            }
            onNodeWithText(lastUpdate).assertIsDisplayed()
        }
    }


}
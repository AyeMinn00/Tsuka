package com.ayeminoo.tsuka.navi

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import com.ayeminoo.tsuka.DataProvider
import com.ayeminoo.tsuka.HiltActivity
import com.ayeminoo.tsuka.data.FakeCurrencyRepo
import com.ayeminoo.tsuka.ui.CurrencyHomeScreen
import com.ayeminoo.tsuka.ui.MainViewModel
import com.ayeminoo.tsuka.ui.basecurrency.BaseCurrencySelectionScreen
import com.ayeminoo.tsuka.ui.basecurrency.BaseCurrencySelectionViewModel
import com.ayeminoo.tsuka.util.assertCurrentRouteName
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltActivity>()
    private lateinit var navController: TestNavHostController

    private val viewModel = BaseCurrencySelectionViewModel(FakeCurrencyRepo())

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavHost(
                navController = navController, startDestination = Destinations.HOME_ROUTE
            ) {
                composable(Destinations.HOME_ROUTE) {
                    CurrencyHomeScreen(
                        viewModel = hiltViewModel(),
                        onSelectBaseCurrency = {
                            navController.navigate(Destinations.BASE_CURRENCY_SELECTION_ROUTE)
                        })
                }
                composable(Destinations.BASE_CURRENCY_SELECTION_ROUTE) {
                    BaseCurrencySelectionScreen(
                        viewModel = viewModel,
                        onSelectBaseCurrency = {
                            navController.popBackStack()
                        })
                }
            }
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.HOME_ROUTE)
    }

    @Test
    fun appNavHost_clickOnCurrentCurrency_navigatesToSelectionBaseCurrency() {
        navigateToBaseCurrencySelection()
        navController.assertCurrentRouteName(Destinations.BASE_CURRENCY_SELECTION_ROUTE)
    }

    @Test
    fun appNavHost_selectBaseCurrencyOnBaseCurrencySelectionScreen_navigatesBackToHome() {
        navigateToBaseCurrencySelection()
        composeTestRule.onNodeWithText(DataProvider.sampleCurrencies[0].currencyCode).performClick()
        navController.assertCurrentRouteName(Destinations.HOME_ROUTE)
    }

    private fun navigateToBaseCurrencySelection(){
        composeTestRule.onNodeWithTag("base_currency")
            .performClick()
    }


}
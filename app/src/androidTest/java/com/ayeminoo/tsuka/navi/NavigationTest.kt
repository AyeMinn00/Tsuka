package com.ayeminoo.tsuka.navi

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.ayeminoo.tsuka.HiltActivity
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

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            TsukaNavHost(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.HOME_ROUTE)
    }

    @Test
    fun appNavHost_clickOnCurrentCurrency_navigatesToSelectionBaseCurrency() {
        composeTestRule.onNodeWithTag("base_currency")
            .performClick()
        navController.assertCurrentRouteName(Destinations.BASE_CURRENCY_SELECTION_ROUTE)
    }


}
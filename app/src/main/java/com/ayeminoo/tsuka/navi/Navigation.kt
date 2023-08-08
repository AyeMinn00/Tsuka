package com.ayeminoo.tsuka.navi

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayeminoo.tsuka.navi.Destinations.BASE_CURRENCY_SELECTION_ROUTE
import com.ayeminoo.tsuka.navi.Destinations.HOME_ROUTE
import com.ayeminoo.tsuka.ui.CurrencyHomeScreen
import com.ayeminoo.tsuka.ui.basecurrency.BaseCurrencySelectionScreen


@Composable
fun TsukaNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController, startDestination = HOME_ROUTE
    ) {
        composable(HOME_ROUTE) {
            CurrencyHomeScreen(
                viewModel = hiltViewModel(),
                onSelectBaseCurrency = {
                    navController.navigate(BASE_CURRENCY_SELECTION_ROUTE)
                })
        }
        composable(BASE_CURRENCY_SELECTION_ROUTE) {
            BaseCurrencySelectionScreen(
                viewModel = hiltViewModel(),
                onSelectBaseCurrency = {
                    navController.popBackStack()
                })
        }
    }
}
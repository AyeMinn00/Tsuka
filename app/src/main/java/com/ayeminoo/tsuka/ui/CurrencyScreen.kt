package com.ayeminoo.tsuka.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.ui.composables.CurrencyList

@Composable
fun CurrencyScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val currencies: List<Currency> by viewModel.currencies.collectAsState()

    CurrencyList(
        modifier = modifier,
        data = currencies
    )
}
package com.ayeminoo.tsuka.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.ui.composables.CurrencyHomeContent

@Composable
fun CurrencyHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val currencies: List<Currency> by viewModel.currencies.collectAsState()
    val amount : String by viewModel.amount.collectAsState()

    CurrencyHomeContent(
        modifier = modifier,
        data = currencies,
        amount = amount,
        updatedTime = "12/2/2022",
        onClickUpdate = viewModel::refreshData,
        onUpdateAmount = viewModel::convertAmount
    )
}

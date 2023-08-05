package com.ayeminoo.tsuka.ui.basecurrency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayeminoo.tsuka.models.Currency

@Composable
fun BaseCurrencySelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: BaseCurrencySelectionViewModel,
    onSelectBaseCurrency: () -> Unit
) {

    val currencies by viewModel.currencies.collectAsState(emptyList())

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Select Base Currency", color = Color.Black,
            style = TextStyle(
                fontSize = 18.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(horizontal = 8.dp, vertical = 16.dp)
        )
        AvailableCurrencyList(
            modifier = Modifier.weight(1f, fill = true),
            data = currencies,
            onSelect = {
                viewModel.selectBaseCurrency(it)
                onSelectBaseCurrency()
            }
        )
    }

}


@Composable
fun AvailableCurrencyList(
    modifier: Modifier = Modifier,
    data: List<Currency>,
    onSelect: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        content = {
            items(
                data,
                key = { model -> model }
            ) { currency ->
                BaseCurrencyCodeRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    currency = currency,
                    onSelect = {
                        onSelect(currency.currencyCode)
                    }
                )
            }
        })

}

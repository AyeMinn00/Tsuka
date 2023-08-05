package com.ayeminoo.tsuka.ui.basecurrency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.theme.TsukaTheme

@Composable
fun BaseCurrencyCodeRow(
    modifier: Modifier = Modifier,
    currency: Currency,
    onSelect: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onSelect()
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (currency.isBase)
            Icon(imageVector = Icons.Filled.Check, contentDescription = null)
        Text(
            text = currency.currencyCode,
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BaseCurrencyCodeRowPreview() {
    TsukaTheme {
        BaseCurrencyCodeRow(
            currency = Currency(
                currencyCode = "JPY",
                amount = "12.2",
                isBase = true
            ),
            onSelect = {}
        )
    }
}
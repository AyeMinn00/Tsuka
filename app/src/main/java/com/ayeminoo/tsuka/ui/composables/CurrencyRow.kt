package com.ayeminoo.tsuka.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ayeminoo.tsuka.models.Currency

@Composable
fun CurrencyRow(
    modifier: Modifier = Modifier,
    currency: Currency
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = currency.currencyCode,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Text(
            text = currency.amount.toString(),
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
    }
}


@Preview(
    showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CurrencyRow_Preview() {
    CurrencyRow(
        modifier = Modifier.fillMaxWidth(),
        currency = Currency(currencyCode = "USD", 234.567f)
    )
}
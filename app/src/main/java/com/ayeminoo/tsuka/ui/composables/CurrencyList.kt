package com.ayeminoo.tsuka.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayeminoo.tsuka.models.Currency

@Composable
fun CurrencyList(
    modifier: Modifier = Modifier,
    data: List<Currency>
) {


    LazyColumn(
        modifier = modifier,
        content = {
            items(
                data,
                key = { model -> model }
            ) { currency ->
                CurrencyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    currency = currency
                )
            }
        })

}

@Preview(
    device = "id:pixel_2", showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CurrencyList_Preview() {
    val data = listOf(Currency("USD", 0.23f), Currency("THB", 1243f), Currency("JPY", 527329f))
    CurrencyList(data = data)
}

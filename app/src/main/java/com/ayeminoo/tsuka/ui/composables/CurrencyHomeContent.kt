package com.ayeminoo.tsuka.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ayeminoo.tsuka.models.Currency

@Composable
fun CurrencyHomeContent(
    modifier: Modifier = Modifier,
    data: List<Currency>,
    updatedTime: String,
    onClickUpdate: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        CurrencyList(
            modifier = Modifier.weight(1f, fill = true),
            data = data
        )
        UpdateStatusBar(
            updatedTime = updatedTime,
            onUpdate = onClickUpdate
        )
    }

}

@Preview(
    showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CurrencyHomeContentPreview() {
    val data = listOf(Currency("USD", 0.23f), Currency("THB", 1243f), Currency("JPY", 527329f))
    CurrencyHomeContent(
        data = data,
        updatedTime = "12/2/2022",
        onClickUpdate = { }
    )
}
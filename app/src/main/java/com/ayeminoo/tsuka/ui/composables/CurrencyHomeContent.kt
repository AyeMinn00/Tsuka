package com.ayeminoo.tsuka.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.models.InputType
import com.ayeminoo.tsuka.theme.TsukaTheme
import com.ayeminoo.tsuka.utils.Calculator

@Composable
fun CurrencyHomeContent(
    modifier: Modifier = Modifier,
    data: List<Currency>,
    baseCurrency: String,
    amount: String,
    updatedTime: String,
    onClickUpdate: () -> Unit,
    onUpdateAmount: (String) -> Unit,
    onClickBaseCurrency: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        // Input for amount
        BaseCurrencyAndInputRow(
            baseCurrency = baseCurrency,
            amount = amount,
            onClickBaseCurrency = onClickBaseCurrency
        )

        // Available Currencies
        CurrencyList(
            modifier = Modifier.weight(1f, fill = true), data = data
        )

        // Number Inputs
        NumberPad(
            onInput = { type ->
                when (type) {
                    is InputType.Number -> {
                        onUpdateAmount(
                            Calculator.addInput(
                                prevStr = amount,
                                type.value
                            )
                        )
                    }

                    is InputType.Clear -> {
                        onUpdateAmount(Calculator.clear())
                    }

                    is InputType.Point -> {
                        onUpdateAmount(Calculator.addDecimalPoint(amount))
                    }

                    is InputType.RemoveOnce -> {
                        onUpdateAmount(Calculator.removeOnce(amount))
                    }
                }
            })

        // To show updated rate
        UpdateStatusBar(
            modifier = Modifier.background(Color.LightGray),
            updatedTime = updatedTime,
            onUpdate = onClickUpdate
        )
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CurrencyHomeContentPreview() {
    val data = listOf(Currency("USD", "0.23"), Currency("THB", "1243"), Currency("JPY", "527329"))
    TsukaTheme {
        CurrencyHomeContent(data = data,
            updatedTime = "12/2/2022",
            baseCurrency = "THB",
            amount = "1.3",
            onClickUpdate = { },
            onUpdateAmount = {},
            onClickBaseCurrency = {}
        )
    }
}

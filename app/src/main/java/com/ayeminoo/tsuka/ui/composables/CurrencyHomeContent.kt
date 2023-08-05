package com.ayeminoo.tsuka.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.models.InputType
import com.ayeminoo.tsuka.utils.Calculator

@Composable
fun CurrencyHomeContent(
    modifier: Modifier = Modifier,
    data: List<Currency>,
    amount: String,
    updatedTime: String,
    onClickUpdate: () -> Unit,
    onUpdateAmount: (String) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            text = amount,
            color = Color.Black,
            textAlign = TextAlign.End
        )

        CurrencyList(
            modifier = Modifier.weight(1f, fill = true), data = data
        )
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
    val data = listOf(Currency("USD", "0.23"), Currency("THB", "1243"), Currency("JPY", "527329f"))
    CurrencyHomeContent(data = data,
        updatedTime = "12/2/2022",
        amount = "1.3",
        onClickUpdate = { },
        onUpdateAmount = {})
}
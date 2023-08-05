package com.ayeminoo.tsuka.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayeminoo.tsuka.theme.TsukaTheme

@Composable
fun BaseCurrencyAndInputRow(
    modifier: Modifier = Modifier,
    baseCurrency: String,
    amount: String,
    onClickBaseCurrency: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .clickable(enabled = true) {
                    onClickBaseCurrency()
                }
                .padding(vertical = 16.dp, horizontal = 16.dp),
        ) {
            Text(
                text = baseCurrency,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
                .padding(vertical = 16.dp, horizontal = 16.dp),
            text = amount,
            color = Color.Black,
            textAlign = TextAlign.End,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BaseCurrencyAndInputRowPreview() {
    TsukaTheme {
        BaseCurrencyAndInputRow(baseCurrency = "USD", amount = "123.33", onClickBaseCurrency = {})
    }
}
package com.ayeminoo.tsuka.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayeminoo.tsuka.models.InputType
import com.ayeminoo.tsuka.models.InputType.Clear
import com.ayeminoo.tsuka.models.InputType.Number
import com.ayeminoo.tsuka.models.InputType.Point
import com.ayeminoo.tsuka.models.InputType.RemoveOnce

@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    onInput: (InputType) -> Unit
) {

    Column(
        modifier = modifier
            .background(Color.LightGray)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "1",
                icon = null,
                onClick = { onInput(Number("1")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "2", icon = null,
                onClick = { onInput(Number("2")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "3", icon = null,
                onClick = { onInput(Number("3")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "4", icon = null,
                onClick = { onInput(Number("4")) }
            )
        }

        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "5", icon = null,
                onClick = { onInput(Number("5")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "6", icon = null,
                onClick = { onInput(Number("6")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "7", icon = null,
                onClick = { onInput(Number("7")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = ".", icon = null,
                onClick = { onInput(Point) }
            )
        }

       // Third Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "8", icon = null,
                onClick = { onInput(Number("8")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "9", icon = null,
                onClick = { onInput(Number("9")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = "0", icon = null,
                onClick = { onInput(Number("0")) }
            )
            ClickInput(
                modifier = Modifier.weight(1f),
                text = null, icon = Icons.Outlined.KeyboardArrowLeft,
                onClick = { onInput(RemoveOnce) },
                onLongPress = { onInput(Clear)}
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_2")
@Composable
fun NumberPadPreview() {
    NumberPad(
        modifier = Modifier.fillMaxWidth(),
        onInput = {}
    )
}
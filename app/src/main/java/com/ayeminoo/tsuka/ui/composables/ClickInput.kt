package com.ayeminoo.tsuka.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClickInput(
    modifier: Modifier = Modifier,
    text: String?,
    icon: ImageVector?,
    onClick: () -> Unit,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 16.dp)
) {
    Box(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable(enabled = true) {
                onClick()
            }
            .padding(paddingValues)
            .height(32.dp)
    ) {
        text?.let {
            Text(
                text = it, textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.Center),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
            )
        }
        icon?.let {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun ClickInputPreviewForText() {
    ClickInput(
        text = "1",
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ClickInputPreviewForIcon() {
    ClickInput(
        text = null,
        icon = Icons.Filled.KeyboardArrowLeft,
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
    )
}
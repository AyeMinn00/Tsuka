package com.ayeminoo.tsuka.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UpdateStatusBar(
    modifier: Modifier = Modifier,
    updatedTime: String,
    onUpdate: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = updatedTime,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(1.0f),
            textAlign = Center
        )
        Icon(
            Icons.Filled.Refresh,
            contentDescription = "Refresh",
            modifier = Modifier
                .padding(4.dp)
                .width(32.dp)
                .height(32.dp)
                .background(color = Color.White.copy(alpha = 0f), CircleShape)
                .clickable {
                    onUpdate()
                }
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UpdateStatusBarPreview() {
    UpdateStatusBar(
        modifier = Modifier.fillMaxWidth(),
        updatedTime = "12/2/2022",
        onUpdate = { }
    )
}



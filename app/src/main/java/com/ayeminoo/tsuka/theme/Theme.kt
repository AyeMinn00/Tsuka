package com.ayeminoo.tsuka.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BlueGrey900,
    secondary = BlueGrey400
)

private val LightDarkPalette = lightColors(
    primary = BlueGrey500,
    secondary = BlueGrey200
)


@Composable
fun TsukaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val color = if (darkTheme)
        DarkColorPalette else LightDarkPalette

//    val systemUiController = rememberSystemUiController()
//
//    LaunchedEffect(key1 = systemUiController){
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent
//        )
//    }

    MaterialTheme(
        colors = color,
        shapes = Shape,
        typography = Typography,
        content = content
    )
}
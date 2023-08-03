package com.ayeminoo.tsuka.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayeminoo.tsuka.theme.TsukaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsukaTheme {
                CurrencyScreen(
                    modifier = Modifier.fillMaxWidth(),
                    viewModel = hiltViewModel()
                )
            }
        }
    }

}
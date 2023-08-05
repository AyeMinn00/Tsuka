package com.ayeminoo.tsuka.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ayeminoo.tsuka.navi.TsukaNavHost
import com.ayeminoo.tsuka.theme.TsukaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsukaTheme(darkTheme = false) {
//                CurrencyHomeScreen(
//                    modifier = Modifier.fillMaxWidth(),
//                    viewModel = hiltViewModel()
//                )
                TsukaNavHost()
            }
        }
    }

}
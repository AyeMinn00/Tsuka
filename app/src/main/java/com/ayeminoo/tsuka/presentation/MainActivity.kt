package com.ayeminoo.tsuka.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ayeminoo.tsuka.theme.TsukaTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsukaTheme {
                OnboardingScreen()
            }
        }
    }

}
package com.example.tiaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tiaapp.nav.AppNavHost
import com.example.tiaapp.ui.theme.TIAappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TIAappTheme {
                AppNavHost()
            }
        }
    }
}

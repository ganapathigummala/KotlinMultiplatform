package com.example.multimodule

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.runtime.*
import designsystem.AppTheme

import screens.App
import screens.AuthenticationScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        var isChecking = true

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {

            var isEven by remember { mutableStateOf(false) }

            // simulate checking
            LaunchedEffect(Unit) {
                val randomNumber = Random.nextInt(0, 100)
                isEven = randomNumber % 2 == 0
                isChecking = false
            }

            splashScreen.setKeepOnScreenCondition { isChecking }

            if (isEven) {

                AppTheme {
                    App(
                        onLogout = { isEven = false }
                    )
                }
            } else {
                AuthenticationScreen(
                    onLoginSuccess = { isEven = true }
                )
            }
        }
    }
}
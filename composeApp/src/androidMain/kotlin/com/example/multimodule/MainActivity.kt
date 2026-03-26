package com.example.multimodule

import android.content.pm.ActivityInfo
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import navigation.bottomNavItems
import screens.App
import screens.AuthenticationScreen
import screens.HomeScreen
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        var isChecking = true
        var isEven = false

        lifecycleScope.launch {
            val randomNumber = Random.nextInt(0, 100)
            isEven = randomNumber % 2 == 0
            isChecking = false
        }

        splashScreen.setKeepOnScreenCondition { isChecking }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            App(isEven)
        }
    }
}
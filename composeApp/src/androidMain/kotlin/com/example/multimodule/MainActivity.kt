package com.example.multimodule

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import screens.AuthenticationScreen
import kotlin.random.Random

//    class MainActivity : ComponentActivity() {
//        override fun onCreate(savedInstanceState: Bundle?) {

//
//            super.onCreate(savedInstanceState)
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//            // Start Koin DI
//
//
//            setContent {
//                KoinContext {
//                    Scaffold { paddingValues ->
//                        // This padding ensures content is below status bar & above navigation bar
//    //                    HomeScreen(modifier = Modifier.padding(paddingValues))
//                        AuthenticationScreen(modifier = Modifier.padding(paddingValues))
//                    }
//                }
//            }
//        }
//    }
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        var isChecking = true
        var isEven = false
        var isLoggedIn = false

        lifecycleScope.launch {
            val randomNumber = Random.nextInt(0, 100)
            isEven = randomNumber % 2 == 0
            println("Random number = $randomNumber")
//            val token = dataStore.getToken()
//            isLoggedIn = token != null
            isChecking = false
        }

        splashScreen.setKeepOnScreenCondition { isChecking }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {

            Scaffold { paddingValues ->

                if (isEven) {
                    HomeScreen(
                        modifier = Modifier.padding(paddingValues)
                    )
                } else {
                    AuthenticationScreen(
                        modifier = Modifier.padding(paddingValues)
                    )
                }

            }

        }
    }
}


    @Preview(showBackground = true)
    @Composable
    fun AppAndroidPreview() {
        Scaffold { paddingValues ->
            // This padding ensures content is below status bar & above navigation bar
            HomeScreen(modifier = Modifier.padding(paddingValues))
        }}
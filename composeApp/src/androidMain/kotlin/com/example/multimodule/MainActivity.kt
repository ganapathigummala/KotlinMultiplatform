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
import screens.AuthenticationScreen

//    class MainActivity : ComponentActivity() {
//        override fun onCreate(savedInstanceState: Bundle?) {
//            enableEdgeToEdge()
//            var isChecking = true
//    //        lifecycleScope.launch {
//    //            Thread.sleep(10)
//    //            isChecking = false
//    //        }
//    //        installSplashScreen().apply {
//    //            setKeepOnScreenCondition {
//    //                isChecking
//    //            }
//    //        }
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
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            Scaffold { paddingValues ->
                AuthenticationScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenWithInsets() {
        // Handle back button manually if needed
        BackHandler {
            // default behavior is to finish the activity
        }

        Scaffold { paddingValues ->
            // This padding ensures content is below status bar & above navigation bar
    //        HomeScreen(modifier = Modifier.padding(paddingValues))
            AuthenticationScreen(modifier = Modifier.padding(paddingValues))

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AppAndroidPreview() {
        Scaffold { paddingValues ->
            // This padding ensures content is below status bar & above navigation bar
            HomeScreen(modifier = Modifier.padding(paddingValues))
        }}
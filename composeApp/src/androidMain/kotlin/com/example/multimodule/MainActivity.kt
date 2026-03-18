package com.example.multimodule

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
import screens.AuthenticationScreen
import com.example.multimodule.core.network.di.NetworkModule
import com.example.multimodule.feature.home.presentation.di.homeModule
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        var isChecking = true
//        lifecycleScope.launch {
//            Thread.sleep(10)
//            isChecking = false
//        }
//        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                isChecking
//            }
//        }

        super.onCreate(savedInstanceState)

        // Start Koin DI
        startKoin {
            androidContext(this@MainActivity)
            modules(NetworkModule, homeModule)
        }

        setContent {
            KoinContext {
                Scaffold { paddingValues ->
                    // This padding ensures content is below status bar & above navigation bar
//                    HomeScreen(modifier = Modifier.padding(paddingValues))
                    AuthenticationScreen(modifier = Modifier.padding(paddingValues))
                }
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
package com.example.multimodule

import android.app.Application
import com.example.multimodule.core.network.di.NetworkModule
import com.example.multimodule.feature.home.presentation.di.HomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                NetworkModule,
                HomeModule,
                ViewModelModule()
            )
        }
    }
}
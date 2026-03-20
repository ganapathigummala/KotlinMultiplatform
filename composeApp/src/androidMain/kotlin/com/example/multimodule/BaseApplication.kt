package com.example.multimodule

import android.app.Application
import com.example.multimodule.core.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                NetworkModule,
                ViewModelModule()
            )
        }
    }
}
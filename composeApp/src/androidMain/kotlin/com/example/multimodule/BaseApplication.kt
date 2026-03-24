package com.example.multimodule

import Client.networkClientModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@BaseApplication)

            modules(
                networkClientModule,
                ViewModelModule()
            )
        }
    }
}
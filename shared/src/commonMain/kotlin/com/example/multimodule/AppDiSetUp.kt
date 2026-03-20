package com.example.multimodule

import com.example.multimodule.core.network.di.NetworkModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            NetworkModule,
            ViewModelModule()
        )
    }
}
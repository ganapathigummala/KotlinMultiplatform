package com.example.multimodule

import Client.networkClientModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            networkClientModule,
            ViewModelModule()
        )
    }
}
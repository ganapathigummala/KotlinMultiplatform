package com.example.multimodule

import org.koin.core.context.startKoin

fun  initKoin() {
    startKoin {
        modules(
            ViewModelModule()
        )
    }
}
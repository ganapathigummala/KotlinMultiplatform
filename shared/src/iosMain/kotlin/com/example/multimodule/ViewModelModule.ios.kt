package com.example.multimodule

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module
import presentation.SharedViewModel

actual fun ViewModelModule(): Module {
    return module { factory { SharedViewModel() } }
}

class ViewmodelProvider : KoinComponent{
    fun providerSharedViewmodel(): SharedViewModel = get()
}
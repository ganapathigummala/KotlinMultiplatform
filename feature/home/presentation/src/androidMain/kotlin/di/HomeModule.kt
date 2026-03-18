package com.example.multimodule.feature.home.presentation.di

import com.example.multimodule.feature.home.presentation.viewmodel.ApodViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::ApodViewModel)
}
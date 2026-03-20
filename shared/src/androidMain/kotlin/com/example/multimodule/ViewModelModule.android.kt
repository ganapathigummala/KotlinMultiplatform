package com.example.multimodule

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import viewmodels.ApodViewModel
import viewmodels.SharedViewModel

actual fun ViewModelModule(): Module {
    return module {

        viewModel {
            SharedViewModel()
        }

        viewModel {
            ApodViewModel(get())
        }

    }
}
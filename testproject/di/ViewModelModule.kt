package com.karan.testproject.di

import com.karan.testproject.viewmodels.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        ImageViewModel(get())
    }
}
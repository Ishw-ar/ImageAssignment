package com.karan.testproject.di

import com.karan.testproject.repository.ImageRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ImageRepository(get())
    }
}
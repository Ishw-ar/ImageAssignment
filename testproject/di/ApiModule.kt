package com.karan.testproject.di

import com.karan.testproject.api.ImageService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): ImageService{
        return retrofit.create(ImageService::class.java)
    }
    single { provideUseApi(get()) }
}
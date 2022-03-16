package com.example.imagefind.app.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.imagefind.app.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
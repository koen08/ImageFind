package com.example.imagefind.app.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.imagefind.app.ui.fragments.favorite.FavoriteViewModel
import com.example.imagefind.app.ui.fragments.wall.MainViewModel
import com.example.imagefind.domain.usecase.AddImageDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageByNameUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Singleton
    abstract fun mainViewModel(mainViewModel: MainViewModel) : ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    @Singleton
    abstract fun favoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel
}
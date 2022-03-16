package com.example.imagefind.app.di

import com.example.imagefind.app.di.viewmodel.ViewModelFactoryModule
import com.example.imagefind.app.di.viewmodel.ViewModelModule
import com.example.imagefind.app.ui.fragments.favorite.ImageLikeFragment
import com.example.imagefind.app.ui.fragments.wall.ImageWallFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [ImageListModule::class, RetrofitModule::class, DataBaseModule::class,
        ViewModelFactoryModule::class, ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(imageWallFragment: ImageWallFragment)
    fun inject(imageLikeFragment: ImageLikeFragment)
}
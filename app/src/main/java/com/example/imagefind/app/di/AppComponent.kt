package com.example.imagefind.app.di

import com.example.imagefind.app.ui.MainActivity
import com.example.imagefind.app.ui.fragments.ImageWallFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ImageListModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(imageWallFragment: ImageWallFragment)
}
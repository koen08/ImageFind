package com.example.imagefind.app.di

import com.example.imagefind.app.ui.MainViewModel
import com.example.imagefind.app.ui.ViewModelFactory
import com.example.imagefind.data.api.ImageApi
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.data.network.ImageListNetworkImpl
import com.example.imagefind.data.service.ImageServiceImpl
import com.example.imagefind.domain.service.ImageService
import com.example.imagefind.domain.usecase.GetImageByName
import dagger.Module
import dagger.Provides

@Module
class ImageListModule {
    @Provides
    fun provideImageListNetwork(imageApi: ImageApi): ImageListNetwork =
        ImageListNetworkImpl(imageApi)

    @Provides
    fun provideImageService(imageListNetwork: ImageListNetwork): ImageService {
        return ImageServiceImpl(imageListNetwork)
    }
}
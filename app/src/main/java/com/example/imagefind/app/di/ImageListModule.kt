package com.example.imagefind.app.di

import com.example.imagefind.data.api.ImageApi
import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.ImageDaoDatabase
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.data.network.ImageListNetworkImpl
import com.example.imagefind.data.service.ImageDaoImpl
import com.example.imagefind.data.service.ImageRepositoryImpl
import com.example.imagefind.data.service.ImageRoomRepositoryImpl
import com.example.imagefind.domain.service.ImageRepository
import com.example.imagefind.domain.service.ImageRoomRepository
import dagger.Module
import dagger.Provides

@Module
class ImageListModule {
    @Provides
    fun provideImageListNetwork(imageApi: ImageApi): ImageListNetwork =
        ImageListNetworkImpl(imageApi)

    @Provides
    fun provideImageService(imageListNetwork: ImageListNetwork): ImageRepository {
        return ImageRepositoryImpl(imageListNetwork)
    }

    @Provides
    fun provideImageDao(imageDaoDatabase: ImageDaoDatabase) : ImageDao {
        return ImageDaoImpl(imageDaoDatabase)
    }

    @Provides
    fun provideImageServiceRoom(imageDao: ImageDao) : ImageRoomRepository {
        return ImageRoomRepositoryImpl(imageDao)
    }
}
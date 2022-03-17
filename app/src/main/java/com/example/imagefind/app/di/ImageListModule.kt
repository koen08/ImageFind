package com.example.imagefind.app.di

import com.example.imagefind.data.api.ImageApi
import com.example.imagefind.data.database.FavoriteListPagingSource
import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.ImageDaoDatabase
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.data.network.ImageListNetworkImpl
import com.example.imagefind.data.network.ImageListPagingSource
import com.example.imagefind.data.service.FavoritePagingImpl
import com.example.imagefind.data.service.ImageDaoImpl
import com.example.imagefind.data.service.ImageNetworkPagingImpl
import com.example.imagefind.data.service.ImageRoomRepositoryImpl
import com.example.imagefind.domain.service.FavoritePaging
import com.example.imagefind.domain.service.ImageNetworkPaging
import com.example.imagefind.domain.service.ImageRoomRepository
import dagger.Module
import dagger.Provides

@Module
class ImageListModule {
    @Provides
    fun provideImageListNetwork(imageApi: ImageApi): ImageListNetwork =
        ImageListNetworkImpl(imageApi)

    @Provides
    fun provideImageService(imageListPagingSource: ImageListPagingSource.Factory): ImageNetworkPaging {
        return ImageNetworkPagingImpl(imageListPagingSource)
    }

    @Provides
    fun provideImageDao(imageDaoDatabase: ImageDaoDatabase): ImageDao {
        return ImageDaoImpl(imageDaoDatabase)
    }

    @Provides
    fun provideFavoritePaging(favoriteListPagingSource: FavoriteListPagingSource): FavoritePaging {
        return FavoritePagingImpl(favoriteListPagingSource)
    }

    @Provides
    fun provideImageServiceRoom(imageDao: ImageDao): ImageRoomRepository {
        return ImageRoomRepositoryImpl(imageDao)
    }
}
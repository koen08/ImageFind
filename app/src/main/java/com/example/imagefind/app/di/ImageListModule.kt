package com.example.imagefind.app.di

import androidx.lifecycle.ViewModel
import com.example.imagefind.app.ui.fragments.wall.MainViewModel
import com.example.imagefind.app.ui.fragments.wall.ViewModelFactory
import com.example.imagefind.data.api.ImageApi
import com.example.imagefind.data.database.FavoriteListPagingSource
import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.ImageDaoDatabase
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.data.network.ImageListNetworkImpl
import com.example.imagefind.data.network.ImageListPagingSource
import com.example.imagefind.data.service.FavoritePagingImpl
import com.example.imagefind.data.service.ImageDaoImpl
import com.example.imagefind.data.service.ImageRepositoryImpl
import com.example.imagefind.data.service.ImageRoomRepositoryImpl
import com.example.imagefind.domain.service.FavoritePaging
import com.example.imagefind.domain.service.ImageRepository
import com.example.imagefind.domain.service.ImageRoomRepository
import com.example.imagefind.domain.usecase.AddImageDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageByNameUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageListModule {
    @Provides
    fun provideImageListNetwork(imageApi: ImageApi): ImageListNetwork =
        ImageListNetworkImpl(imageApi)

    @Provides
    fun provideImageService(imageListPagingSource: ImageListPagingSource.Factory): ImageRepository {
        return ImageRepositoryImpl(imageListPagingSource)
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

    @Provides
    @Singleton
    fun viewModelFactory(mainViewModel: MainViewModel): ViewModelFactory {
        return ViewModelFactory(mainViewModel)
    }

    @Provides
    @Singleton
    fun mainViewModel(getImageByNameUseCase: GetImageByNameUseCase,
                         addImageDatabaseUseCase: AddImageDatabaseUseCase
    ): MainViewModel {
        return MainViewModel(getImageByNameUseCase, addImageDatabaseUseCase)
    }
}
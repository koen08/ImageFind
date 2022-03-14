package com.example.imagefind.domain.usecase

import androidx.paging.PagingData
import com.example.imagefind.domain.models.ImageFavorite
import com.example.imagefind.domain.models.ImageFavoriteList
import com.example.imagefind.domain.service.FavoritePaging
import com.example.imagefind.domain.service.ImageRoomRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetImageFavoriteRoomUseCase @Inject constructor(private val favoritePaging: FavoritePaging) {
    fun getAll(): Flowable<PagingData<ImageFavorite>> {
        return favoritePaging.get()
    }
}
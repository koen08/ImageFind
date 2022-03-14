package com.example.imagefind.domain.service

import androidx.paging.PagingData
import com.example.imagefind.domain.models.ImageFavorite
import io.reactivex.rxjava3.core.Flowable

interface FavoritePaging {
    fun get(): Flowable<PagingData<ImageFavorite>>
}
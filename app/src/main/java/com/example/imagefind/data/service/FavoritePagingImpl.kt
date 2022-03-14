package com.example.imagefind.data.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.imagefind.data.database.FavoriteListPagingSource
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.models.ImageFavorite
import com.example.imagefind.domain.service.FavoritePaging
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class FavoritePagingImpl @Inject constructor(private val favoriteListPagingSource: FavoriteListPagingSource) :
    FavoritePaging {
    override fun get(): Flowable<PagingData<ImageFavorite>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { favoriteListPagingSource }
        ).flowable
    }
}
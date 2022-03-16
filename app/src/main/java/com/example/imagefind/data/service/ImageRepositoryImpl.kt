package com.example.imagefind.data.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.imagefind.data.network.ImageListPagingSource
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.service.ImageRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: ImageListPagingSource.Factory
) : ImageRepository {
    override fun get(
        name: String,
        orientation: String,
        imageType: String,
        order: String
    ): Flowable<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
                prefetchDistance = 2,
                maxSize = 20
            ),
            pagingSourceFactory = {
                pagingSourceFactory.create(
                    name,
                    orientation,
                    imageType,
                    order
                )
            }
        ).flowable
    }
}
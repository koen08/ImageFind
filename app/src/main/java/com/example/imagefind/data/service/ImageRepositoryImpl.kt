package com.example.imagefind.data.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.imagefind.data.network.ImageListPagingSource
import com.example.imagefind.data.network.models.ImageNet
import com.example.imagefind.domain.service.ImageRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: ImageListPagingSource.Factory
) :
    ImageRepository {
    override fun get(name: String): Flowable<PagingData<ImageNet>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { pagingSourceFactory.create(name) }
        ).flowable
    }


    /*override fun get(name: String): Single<List<Image>> {
        return imageListNetwork.getImageList(name, page).map { imageListNetwork ->
            imageListNetwork.hits.map { imageNet ->
                Image(
                    imageNet.id,
                    imageNet.url,
                    imageNet.userImageURL,
                    imageNet.userName,
                    imageNet.likes,
                    imageNet.views
                )
            }
        }
    }*/

}
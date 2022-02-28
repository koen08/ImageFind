package com.example.imagefind.data.network

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.imagefind.domain.models.Image
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ImageListPagingSource @AssistedInject constructor(
    private val imageListNetwork: ImageListNetwork,
    @Assisted("name") val name: String
) : RxPagingSource<Int, Image>() {
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Image>> {
        val pageNumber = params.key ?: 1
        val response = imageListNetwork.getImageList(name, pageNumber)
        return response.subscribeOn(Schedulers.io()).map {
            LoadResult.Page(
                data = it.hits.map { imageNet ->
                    Image(
                        imageNet.id,
                        imageNet.url,
                        imageNet.userImageURL,
                        imageNet.userName,
                        imageNet.likes,
                        imageNet.views
                    )
                },
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (it.hits.isEmpty()) null else pageNumber + 1
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("name") query: String): ImageListPagingSource
    }
}
package com.example.imagefind.data.database

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.models.ImageFavorite
import com.example.imagefind.domain.models.ImageFavoriteList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FavoriteListPagingSource @Inject constructor(val imageDao: ImageDao) :
    RxPagingSource<Int, ImageFavorite>() {
    override fun getRefreshKey(state: PagingState<Int, ImageFavorite>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ImageFavorite>> {
        val pageNumber = params.key ?: 1
        val offset = (pageNumber - 1) * 10
        val response = imageDao.getAll(10, offset)
        return response.subscribeOn(Schedulers.io()).map {
            LoadResult.Page(
                data = it.map { imageTable ->
                    ImageFavorite(
                        imageTable.imageId,
                        imageTable.imageUrl
                    )
                },
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (it.isEmpty()) null else pageNumber + 1
            )
        }
    }
}
package com.example.imagefind.domain.service

import androidx.paging.PagingData
import com.example.imagefind.data.network.models.ImageNet
import com.example.imagefind.domain.models.Image
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ImageRepository {
    fun get(name: String): Flowable<PagingData<ImageNet>>
}
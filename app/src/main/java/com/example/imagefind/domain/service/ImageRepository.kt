package com.example.imagefind.domain.service

import androidx.paging.PagingData
import com.example.imagefind.domain.models.Image
import io.reactivex.rxjava3.core.Flowable

interface ImageRepository {
    fun get(name: String): Flowable<PagingData<Image>>
}
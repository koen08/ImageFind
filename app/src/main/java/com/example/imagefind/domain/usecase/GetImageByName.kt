package com.example.imagefind.domain.usecase

import com.example.imagefind.data.network.models.ImageListNet
import com.example.imagefind.domain.service.ImageService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetImageByName @Inject constructor(private val imageService: ImageService) {
    fun get(name: String): Single<ImageListNet> {
        return imageService.get(name)
    }
}
package com.example.imagefind.domain.usecase

import com.example.imagefind.data.network.models.ImageListNet
import com.example.imagefind.domain.service.ImageRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetImageByNameUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    fun get(name: String): Single<ImageListNet> {
        return imageRepository.get(name)
    }
}
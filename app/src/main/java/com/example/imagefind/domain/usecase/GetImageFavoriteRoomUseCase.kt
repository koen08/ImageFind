package com.example.imagefind.domain.usecase

import com.example.imagefind.domain.models.ImageFavoriteList
import com.example.imagefind.domain.service.ImageRoomRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetImageFavoriteRoomUseCase @Inject constructor(private val imageRoomRepository: ImageRoomRepository) {
    fun getAll(): Single<ImageFavoriteList> {
        return imageRoomRepository.getAll()
    }
}
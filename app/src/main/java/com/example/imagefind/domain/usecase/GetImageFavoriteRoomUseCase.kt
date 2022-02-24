package com.example.imagefind.domain.usecase

import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.service.ImageRoomRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetImageFavoriteRoomUseCase @Inject constructor(private val imageRoomRepository: ImageRoomRepository) {
    fun getAll(): Single<List<ImageTable>> {
        return imageRoomRepository.getAll()
    }
}
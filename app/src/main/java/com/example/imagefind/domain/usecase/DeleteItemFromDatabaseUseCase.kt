package com.example.imagefind.domain.usecase

import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.service.ImageRoomRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteItemFromDatabaseUseCase @Inject constructor(private val imageRoomRepository: ImageRoomRepository) {
    fun delete(imageTable: ImageTable): Completable {
        return imageRoomRepository.delete(imageTable)
    }
}
package com.example.imagefind.domain.usecase

import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.service.ImageServiceRoom
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddImageDatabaseUseCase @Inject constructor(private val imageServiceRoom: ImageServiceRoom) {
    fun add(imageTable: ImageTable) : Completable {
        return imageServiceRoom.insert(imageTable)
    }
}
package com.example.imagefind.domain.usecase

import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.models.ImageTable
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddImageDatabaseUseCase @Inject constructor(private val imageDao: ImageDao) {
    fun add(imageTable: ImageTable) : Completable {
        return imageDao.insert(imageTable)
    }
}
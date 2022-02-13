package com.example.imagefind.domain.usecase

import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.models.ImageTable
import javax.inject.Inject

class AddImageDatabase @Inject constructor(private val imageDao: ImageDao) {
    fun add(imageTable: ImageTable) {
        imageDao.insert(imageTable)
    }
}
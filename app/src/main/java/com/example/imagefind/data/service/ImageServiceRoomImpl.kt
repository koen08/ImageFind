package com.example.imagefind.data.service

import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.service.ImageServiceRoom
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImageServiceRoomImpl @Inject constructor(val imageDao: ImageDao): ImageServiceRoom {
    override fun insert(imageTable: ImageTable): Completable {
        return imageDao.insert(imageTable)
    }

    override fun getAll(): Single<List<ImageTable>> {
        return imageDao.getAll()
    }
}
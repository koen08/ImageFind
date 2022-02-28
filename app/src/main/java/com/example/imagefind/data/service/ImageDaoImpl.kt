package com.example.imagefind.data.service

import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.ImageDaoDatabase
import com.example.imagefind.data.database.models.ImageTable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ImageDaoImpl(private val imageDaoDataBase: ImageDaoDatabase) : ImageDao {
    override fun insert(imageTable: ImageTable): Completable {
        return imageDaoDataBase.insert(imageTable)
    }

    override fun delete(imageTable: ImageTable): Completable {
        return imageDaoDataBase.delete(imageTable)
    }

    override fun getAll(): Single<List<ImageTable>> {
        return imageDaoDataBase.getAll()
    }
}
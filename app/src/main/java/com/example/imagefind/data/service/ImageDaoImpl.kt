package com.example.imagefind.data.service

import com.example.imagefind.data.database.ImageDao
import com.example.imagefind.data.database.ImageDaoDatabase
import com.example.imagefind.data.models.ImageTable

class ImageDaoImpl(private val imageDaoDataBase: ImageDaoDatabase) : ImageDao {
    override fun insert(imageTable: ImageTable) {
        imageDaoDataBase.insert(imageTable)
    }

    override fun delete(imageTable: ImageTable) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<ImageTable> {
        TODO("Not yet implemented")
    }
}
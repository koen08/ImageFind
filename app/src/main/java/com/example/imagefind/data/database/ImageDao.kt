package com.example.imagefind.data.database

import com.example.imagefind.data.database.models.ImageTable

interface ImageDao {
    fun insert(imageTable: ImageTable)
    fun delete(imageTable: ImageTable)
    fun getAll(): List<ImageTable>
}
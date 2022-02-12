package com.example.imagefind.data.database

import androidx.room.Delete
import androidx.room.Query
import com.example.imagefind.data.models.ImageTable

interface ImageDao {
    fun insert(imageTable: ImageTable)
    fun delete(imageTable: ImageTable)
    fun getAll(): List<ImageTable>
}
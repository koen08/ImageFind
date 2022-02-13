package com.example.imagefind.data.database

import androidx.room.*
import com.example.imagefind.data.database.models.ImageTable

@Dao
interface ImageDaoDatabase {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageTable: ImageTable)
    @Delete
    fun delete(imageTable: ImageTable)
    @Query("SELECT * FROM image_table")
    fun getAll(): List<ImageTable>
}
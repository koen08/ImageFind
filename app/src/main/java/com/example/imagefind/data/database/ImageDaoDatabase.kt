package com.example.imagefind.data.database

import androidx.room.*
import com.example.imagefind.data.database.models.ImageTable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ImageDaoDatabase {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageTable: ImageTable): Completable

    @Delete
    fun delete(imageTable: ImageTable)

    @Query("SELECT * FROM image_table")
    fun getAll(): Single<List<ImageTable>>
}
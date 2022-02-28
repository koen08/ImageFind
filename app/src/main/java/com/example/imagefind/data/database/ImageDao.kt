package com.example.imagefind.data.database

import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.models.ImageFavoriteList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ImageDao {
    fun insert(imageTable: ImageTable): Completable
    fun delete(imageTable: ImageTable): Completable
    fun getAll(): Single<List<ImageTable>>
}
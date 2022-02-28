package com.example.imagefind.domain.service

import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.models.ImageFavoriteList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ImageRoomRepository {
    fun insert(imageTable: ImageTable): Completable
    fun getAll(): Single<ImageFavoriteList>
    fun delete(imageTable: ImageTable): Completable
}
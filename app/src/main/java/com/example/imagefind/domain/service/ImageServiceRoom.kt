package com.example.imagefind.domain.service

import com.example.imagefind.data.database.models.ImageTable
import io.reactivex.rxjava3.core.Completable

interface ImageServiceRoom {
    fun insert(imageTable: ImageTable) : Completable
}
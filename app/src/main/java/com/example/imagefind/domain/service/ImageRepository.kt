package com.example.imagefind.domain.service

import com.example.imagefind.data.network.models.ImageListNet
import io.reactivex.rxjava3.core.Single

interface ImageRepository {
    fun get(name: String): Single<ImageListNet>
}
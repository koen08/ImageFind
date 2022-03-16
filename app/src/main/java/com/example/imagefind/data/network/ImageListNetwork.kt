package com.example.imagefind.data.network

import com.example.imagefind.data.network.models.ImageListNet
import io.reactivex.rxjava3.core.Single

interface ImageListNetwork {
    fun getImageList(
        name: String,
        orientation: String,
        imageType: String,
        order: String,
        page: Int
    ): Single<ImageListNet>
}
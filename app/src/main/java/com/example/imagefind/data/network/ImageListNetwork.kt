package com.example.imagefind.data.network

import com.example.imagefind.data.models.ImageListNet
import io.reactivex.rxjava3.core.Single

interface ImageListNetwork {
    fun getImageList(name: String): Single<ImageListNet>
}
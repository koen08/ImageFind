package com.example.imagefind.data.service

import com.example.imagefind.data.network.models.ImageListNet
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.domain.service.ImageRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageListNetwork: ImageListNetwork) :
    ImageRepository {
    override fun get(name: String): Single<ImageListNet> {
        return imageListNetwork.getImageList(name)
    }
}
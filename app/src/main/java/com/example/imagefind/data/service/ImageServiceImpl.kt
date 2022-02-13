package com.example.imagefind.data.service

import com.example.imagefind.data.network.models.ImageListNet
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.domain.service.ImageService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImageServiceImpl @Inject constructor(private val imageListNetwork: ImageListNetwork) :
    ImageService {
    override fun get(name: String): Single<ImageListNet> {
        return imageListNetwork.getImageList(name)
    }
}
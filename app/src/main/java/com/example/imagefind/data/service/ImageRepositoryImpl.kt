package com.example.imagefind.data.service

import com.example.imagefind.data.network.models.ImageListNet
import com.example.imagefind.data.network.ImageListNetwork
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.models.ImageList
import com.example.imagefind.domain.service.ImageRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageListNetwork: ImageListNetwork) :
    ImageRepository {
    override fun get(name: String): Single<List<Image>> {
        return imageListNetwork.getImageList(name).map { imageListNetwork ->
            imageListNetwork.hits.map { imageNet ->
                Image(
                    imageNet.id,
                    imageNet.url,
                    imageNet.userImageURL,
                    imageNet.userName,
                    imageNet.likes,
                    imageNet.views
                )
            }
        }
    }

}
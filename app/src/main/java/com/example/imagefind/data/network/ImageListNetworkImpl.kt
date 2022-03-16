package com.example.imagefind.data.network

import android.graphics.drawable.GradientDrawable
import com.example.imagefind.data.api.ImageApi
import com.example.imagefind.data.network.models.ImageListNet
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImageListNetworkImpl @Inject constructor(val imageApi: ImageApi) : ImageListNetwork {
    override fun getImageList(
        name: String,
        orientation: String,
        imageType: String,
        order: String,
        page: Int
    ): Single<ImageListNet> {
        return imageApi.getImageList(
            "25581308-ddc9b39954bc82683e4afb26b",
            name,
            orientation,
            imageType,
            order,
            page
        )
    }
}
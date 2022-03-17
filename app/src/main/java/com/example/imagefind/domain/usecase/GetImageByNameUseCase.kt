package com.example.imagefind.domain.usecase

import androidx.paging.PagingData
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.service.ImageNetworkPaging
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetImageByNameUseCase @Inject constructor(private val imageRepository: ImageNetworkPaging) {
    fun get(
        name: String,
        orientation: String,
        imageType: String,
        order: String
    ): Flowable<PagingData<Image>> {
        return imageRepository.get(name, orientation, imageType, order)
    }
}
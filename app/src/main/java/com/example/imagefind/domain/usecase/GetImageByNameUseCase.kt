package com.example.imagefind.domain.usecase

import androidx.paging.PagingData
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.service.ImageRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetImageByNameUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    fun get(name: String): Flowable<PagingData<Image>> {
        return imageRepository.get(name)
    }
}
package com.example.imagefind.app.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagefind.data.network.models.ImageListNet
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.models.ImageDto
import com.example.imagefind.domain.models.ImageList
import com.example.imagefind.domain.usecase.AddImageDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageByNameUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getImageByNameUseCase: GetImageByNameUseCase,
    private val addImageDatabaseUseCase: AddImageDatabaseUseCase,
) : ViewModel() {

    private var dispos: Disposable? = null
    private var disAddImageId: Disposable? = null

    private val listImageMutableLive = MutableLiveData<List<ImageDto>>()
    val listImageLiveData: LiveData<List<ImageDto>> = listImageMutableLive

    private val completeMutableAddInfoImage = MutableLiveData<String>()
    val completeAddInfoImage : LiveData<String> = completeMutableAddInfoImage

    fun getImageListByName(name: String) {
        val result = getImageByNameUseCase.get(name)
        dispos = result.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val imageList = networkDaoToImageDomain(it)
                listImageMutableLive.value = imageList.hits
            }, {
                Log.e("AAA", it.localizedMessage!!)
            })
    }

    fun addImageIdToDB(imageId: Long, imageUrl: String) {
        val imageTable = ImageTable(imageId = imageId, imageUrl = imageUrl)
        disAddImageId = addImageDatabaseUseCase.add(imageTable).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                completeMutableAddInfoImage.value = "Image added to favorites"
            }, {
                Log.e("AAA", it.localizedMessage!!)
            })
    }

    private fun networkDaoToImageDomain(imageListNet: ImageListNet): ImageList {
        val imageList = ImageList(ArrayList())
        for (imageDao in imageListNet.hits) {
            imageList.hits.add(
                ImageDto(
                    imageDao.id,
                    imageDao.url,
                    imageDao.userImageURL,
                    imageDao.userName,
                    imageDao.likes,
                    imageDao.views
                )
            )
        }
        return imageList
    }

    fun onDestroy() {
        dispos?.dispose()
        disAddImageId?.dispose()
    }
}
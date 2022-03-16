package com.example.imagefind.app.ui.fragments.wall

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.imagefind.app.ui.fragments.AbstractViewModel
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.models.Image
import com.example.imagefind.domain.usecase.AddImageDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageByNameUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getImageByNameUseCase: GetImageByNameUseCase,
    private val addImageDatabaseUseCase: AddImageDatabaseUseCase,
) : AbstractViewModel() {
    private val listImageMutableLive = MutableLiveData<PagingData<Image>>()
    val listImageLiveData: LiveData<PagingData<Image>> = listImageMutableLive

    var query: String = ""

    var orientationType: String = ""

    var imageType: String = ""

    var order: String = ""

    fun getImageListByName() {
        Log.i("QQQ", orientationType)
        Log.i("QQQ", imageType)
        val disposable =
            getImageByNameUseCase.get(query, orientationType, imageType, order).cachedIn(viewModelScope)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                    listImageMutableLive.value = it
                }, {
                    Log.e("Error", it.localizedMessage!!)
                })
        compositeDisposable.add(disposable)
    }

    fun addImageIdToDB(imageId: Long, imageUrl: String) {
        val imageTable = ImageTable(imageId = imageId, imageUrl = imageUrl)
        val disposable = addImageDatabaseUseCase.add(imageTable).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
        compositeDisposable.add(disposable)
    }
}
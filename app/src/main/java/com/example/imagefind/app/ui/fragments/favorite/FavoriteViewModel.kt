package com.example.imagefind.app.ui.fragments.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.models.ImageFavorite
import com.example.imagefind.domain.models.ImageFavoriteList
import com.example.imagefind.domain.usecase.GetImageFavoriteRoomUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getImageFavoriteRoomUseCase: GetImageFavoriteRoomUseCase
) : ViewModel() {
    private var dispos: Disposable? = null
    private val listImageMutableLive = MutableLiveData<ImageFavoriteList>()
    val listImageLiveData: LiveData<ImageFavoriteList> = listImageMutableLive

    fun getImageAll() {
        dispos = getImageFavoriteRoomUseCase.getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                listImageMutableLive.value = it
            }, {
                Log.e("AAA", it.localizedMessage!!)
            })
    }

    fun onDestroy() {
        dispos?.dispose()
    }
}
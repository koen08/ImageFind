package com.example.imagefind.app.ui.fragments.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.imagefind.app.ui.fragments.AbstractViewModel
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.domain.models.ImageFavorite
import com.example.imagefind.domain.usecase.DeleteItemFromDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageFavoriteRoomUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getImageFavoriteRoomUseCase: GetImageFavoriteRoomUseCase,
    private val deleteItemFromDatabaseUseCase: DeleteItemFromDatabaseUseCase
) : AbstractViewModel() {

    private val listImageMutableLive = MutableLiveData<PagingData<ImageFavorite>>()
    val listImageLiveData: LiveData<PagingData<ImageFavorite>> = listImageMutableLive

    fun getImageAll() {
        val disposable = getImageFavoriteRoomUseCase.getAll().cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                listImageMutableLive.value = it
                Log.d("DEBUG_DATA", "item : $it")
            }, {
                Log.e("Error", it.localizedMessage!!)
            })
        compositeDisposable.add(disposable)
    }

    fun delete(imageTable: ImageTable) {
        val disposable =
            deleteItemFromDatabaseUseCase.delete(imageTable).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    getImageAll()
                }, {
                    Log.e("Error", it.localizedMessage!!)
                })
        compositeDisposable.add(disposable)
    }
}
package com.example.imagefind.app.ui.fragments.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagefind.domain.usecase.DeleteItemFromDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageFavoriteRoomUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getImageFavoriteRoomUseCase: GetImageFavoriteRoomUseCase,
    private val deleteItemFromDatabaseUseCase: DeleteItemFromDatabaseUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FavoriteViewModel(getImageFavoriteRoomUseCase, deleteItemFromDatabaseUseCase) as T
}
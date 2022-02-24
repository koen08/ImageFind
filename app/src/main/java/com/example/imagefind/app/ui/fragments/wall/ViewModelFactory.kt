package com.example.imagefind.app.ui.fragments.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagefind.domain.usecase.AddImageDatabaseUseCase
import com.example.imagefind.domain.usecase.GetImageByNameUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val getImageByNameUseCase: GetImageByNameUseCase,
    private val addImageDatabaseUseCase: AddImageDatabaseUseCase
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(getImageByNameUseCase, addImageDatabaseUseCase) as T
}
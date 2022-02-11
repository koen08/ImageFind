package com.example.imagefind.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagefind.domain.usecase.GetImageByName
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val getImageByName: GetImageByName) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(getImageByName) as T
}
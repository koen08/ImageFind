package com.example.imagefind.app.ui.fragments

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class AbstractViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}
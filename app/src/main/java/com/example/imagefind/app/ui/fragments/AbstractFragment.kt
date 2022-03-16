package com.example.imagefind.app.ui.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagefind.app.ui.ViewModelFactory
import javax.inject.Inject

abstract class AbstractFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    inline fun <reified T : ViewModel> Fragment.injectViewModel(): T {
        return ViewModelProvider(this, viewModelFactory)[T::class.java]
    }
}
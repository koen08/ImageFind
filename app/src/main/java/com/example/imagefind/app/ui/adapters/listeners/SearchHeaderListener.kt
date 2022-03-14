package com.example.imagefind.app.ui.adapters.listeners

import android.media.Image

class SearchHeaderListener: AbstractListener<String>(), SearchHeaderContract {
    var headerListener: ((String) -> Unit)? = { }

    override fun searchEnter(query: String) {
        invoke(query, headerListener)
    }
}
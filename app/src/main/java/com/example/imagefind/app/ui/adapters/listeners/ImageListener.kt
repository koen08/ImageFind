package com.example.imagefind.app.ui.adapters.listeners

import com.example.imagefind.domain.models.Image

class ImageListener: AbstractListener<Image>(), ImageListenerContract {
    var addFavoriteImage: ((Image) -> Unit)? = { }

    override fun invokeImportantListener(image: Image) {
        invoke(image, addFavoriteImage)
    }
}
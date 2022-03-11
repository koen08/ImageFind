package com.example.imagefind.app.ui.adapters.listeners

import android.media.Image
import com.example.imagefind.domain.models.ImageFavorite

class FavoriteListener : AbstractListener<ImageFavorite>(), FavoriteListenerContract<ImageFavorite> {
    var deleteListener: ((ImageFavorite) -> Unit)? = {}
    override fun invokeDeleteImageListener(imageFavorite: ImageFavorite) {
        invoke(imageFavorite, deleteListener)
    }

}
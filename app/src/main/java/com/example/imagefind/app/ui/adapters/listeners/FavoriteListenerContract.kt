package com.example.imagefind.app.ui.adapters.listeners

import com.example.imagefind.domain.models.ImageFavorite

interface FavoriteListenerContract<T: Any> {
    fun invokeDeleteImageListener(imageFavorite: T)
}
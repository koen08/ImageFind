package com.example.imagefind.app.ui.adapters.listeners

import com.example.imagefind.domain.models.ImageFavorite

class FavoriteListenerArgs : ListenerArgs {
    var listener: ((ImageFavorite) -> Unit) = {}

    override fun <T> invoke(item: T) {
        listener.invoke(item as ImageFavorite)
    }
}
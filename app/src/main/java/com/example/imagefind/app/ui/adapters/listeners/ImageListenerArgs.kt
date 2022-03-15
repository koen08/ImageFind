package com.example.imagefind.app.ui.adapters.listeners

import com.example.imagefind.domain.models.Image

class ImageListenerArgs : ListenerArgs {
    var listener: ((Image) -> Unit) = {}

    override fun <T> invoke(item: T) {
        listener.invoke(item as Image)
    }
}
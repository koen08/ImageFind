package com.example.imagefind.app.ui.adapters.listeners

import com.example.imagefind.domain.models.Image

interface ImageListenerContract {
    fun invokeImportantListener(image: Image)
}
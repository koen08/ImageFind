package com.example.imagefind.app.ui.adapters

import android.view.View
import com.example.imagefind.domain.models.Image

interface ListenerClick {
    fun onClick(action: () -> Unit, baseActionListener: BaseActionListener)
}
package com.example.imagefind.app.ui.adapters

interface ListenerClick {
    fun onClick(action: () -> Unit, baseActionListener: BaseActionListener)
}
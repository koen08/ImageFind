package com.example.imagefind.app.ui.adapters.listeners

interface ListenerArgs: Listener {
    fun <T> invoke(item: T)
}
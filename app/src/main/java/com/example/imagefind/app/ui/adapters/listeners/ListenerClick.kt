package com.example.imagefind.app.ui.adapters.listeners

interface ListenerClick<T: Any> {
    fun onClick(item: T, vararg listeners: Listener)
}
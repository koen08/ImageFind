package com.example.imagefind.app.ui.adapters.listeners

abstract class AbstractListener<T : Any> {
    fun invoke(item: T, listener: ((T) -> Unit)?) {
        listener?.invoke(item)
    }
}
package com.example.imagefind.app.ui.adapters.listeners

class SearchHeaderListenerArgs : ListenerArgs {
    var listener: ((String) -> Unit) = {}

    override fun <T> invoke(item: T) {
        listener.invoke(item as String)
    }
}
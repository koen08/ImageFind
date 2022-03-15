package com.example.imagefind.app.ui.adapters.listeners

class AdvanceSearchListenerNoArgs : ListenerArgsNoArgs {
    var listener: (() -> Unit) = {}

    override fun invoke() {
        listener.invoke()
    }
}
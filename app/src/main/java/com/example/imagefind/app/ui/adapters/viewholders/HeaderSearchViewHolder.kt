package com.example.imagefind.app.ui.adapters.viewholders

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.imagefind.app.ui.adapters.AbstractViewHolder
import com.example.imagefind.app.ui.adapters.listeners.AdvanceSearchListenerNoArgs
import com.example.imagefind.app.ui.adapters.listeners.Listener
import com.example.imagefind.app.ui.adapters.listeners.ListenerClick
import com.example.imagefind.app.ui.adapters.listeners.SearchHeaderListenerArgs
import com.example.imagefind.databinding.HeaderSearchBinding

class HeaderSearchViewHolder(itemView: View) :
    AbstractViewHolder<String>(itemView), ListenerClick<String> {
    private val binding by viewBinding(HeaderSearchBinding::bind)

    override fun bind(anyObject: String) {
        with(binding) {

        }
    }

    override fun onClick(item: String, vararg listeners: Listener) {
        with(binding) {
            searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    (listeners[0] as SearchHeaderListenerArgs).invoke(searchEditText.text.toString())
                    return@OnEditorActionListener true
                }
                false
            })
            advanceQueryButton.setOnClickListener {
                (listeners[1] as AdvanceSearchListenerNoArgs).invoke()
            }

        }
    }

}
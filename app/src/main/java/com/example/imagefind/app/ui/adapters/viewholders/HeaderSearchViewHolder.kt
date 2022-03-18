package com.example.imagefind.app.ui.adapters.viewholders

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.imagefind.app.ui.adapters.AbstractViewHolder
import com.example.imagefind.databinding.HeaderSearchBinding

class HeaderSearchViewHolder(itemView: View) :
    AbstractViewHolder<String>(itemView) {
    private val binding by viewBinding(HeaderSearchBinding::bind)

    override fun onClick(vararg listener: (() -> Unit)) {
        with(binding) {

            advanceQueryButton.setOnClickListener {
                listener[0].invoke()
            }

        }
    }

    override fun onClick(vararg listener: (String) -> Unit) {
        with(binding) {

            searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    listener[0].invoke(searchEditText.text.toString())
                    return@OnEditorActionListener true
                }
                false
            })

        }
    }

}
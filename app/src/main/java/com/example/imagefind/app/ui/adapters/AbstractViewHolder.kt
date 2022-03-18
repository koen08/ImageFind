package com.example.imagefind.app.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(anyObject: T) {}
    open fun onClick(vararg listener: (() -> Unit)) {}
    open fun onClick(item: T, vararg listener: ((T) -> Unit)) {}
    open fun onClick(vararg listener: (T) -> Unit) {}
}
package com.example.imagefind.app.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class AbstractViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(anyObject: T)
}
package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.app.ui.adapters.listeners.*
import com.example.imagefind.databinding.HeaderSearchBinding
import com.example.imagefind.databinding.ListImageItemBinding
import com.example.imagefind.domain.models.Image


class ImageListAdapter :
    PagingDataAdapter<Image, RecyclerView.ViewHolder>(ImageListDiffUtils()) {

    val imageListener: ImageListener = ImageListener()
    val searchListener: SearchHeaderListener = SearchHeaderListener()

    class ViewHolder(itemView: View) :
        AbstractViewHolder<Image>(itemView), ListenerClick<Image> {
        private val binding by viewBinding(ListImageItemBinding::bind)

        override fun bind(anyObject: Image) {
            with(binding) {
                this.textNickName.text = anyObject.userName
                val textLikes = anyObject.likes.toString() + " like"
                val textViews = anyObject.view.toString() + " views"
                this.textLikes.text = textLikes
                this.textViews.text = textViews
                Glide.with(imageAvatar).load(anyObject.avatar).into(imageAvatar)
                Glide.with(imageItem).load(anyObject.url).into(imageItem)
            }
        }

        override fun onClick(item: Image, abstractListener: AbstractListener<Image>) {
            with(binding) {
                importantImageView.setOnClickListener {
                    (abstractListener as ImageListenerContract).invokeImportantListener(item)
                }
            }
        }

    }

    class HeaderSearchViewHolder(itemView: View) :
        AbstractViewHolder<String>(itemView), ListenerClick<String> {
        private val binding by viewBinding(HeaderSearchBinding::bind)

        override fun bind(anyObject: String) {
            with(binding) {

            }
        }

        override fun onClick(item: String, abstractListener: AbstractListener<String>) {
            with(binding) {
                searchEditText.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        (abstractListener as SearchHeaderContract).searchEnter(searchEditText.text.toString())
                        return@OnEditorActionListener true
                    }
                    false
                })
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> HeaderSearchViewHolder(
                layoutInflater.inflate(
                    R.layout.header_search,
                    parent,
                    false
                )
            )
            TYPE_ITEM -> ViewHolder(layoutInflater.inflate(R.layout.list_image_item, parent, false))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) TYPE_HEADER else TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder) {
            val item = getItem(position - 1)
            item?.let { holder.bind(it) }
            holder.onClick(item!!, imageListener)
        }
        if (holder is HeaderSearchViewHolder) {
            holder.onClick("qwerty", searchListener)
        }
    }

}
package com.example.imagefind.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imagefind.R
import com.example.imagefind.databinding.ListImageItemBinding
import com.example.imagefind.domain.models.Image

class ImageListAdapter() :
    PagingDataAdapter<Image, ImageListAdapter.ViewHolder>(ImageListDiffUtils()) {
    var importantListener: ((Image) -> Unit)? = { }

    class ViewHolder(itemView: View) :
        AbstractViewHolder<Image>(itemView), ListenerClick {
        private val bind by viewBinding(ListImageItemBinding::bind)

        override fun bind(anyObject: Image) {
            with(bind) {
                this.textNickName.text = anyObject.userName
                val textLikes = anyObject.likes.toString() + " like"
                val textViews = anyObject.view.toString() + " views"
                this.textLikes.text = textLikes
                this.textViews.text = textViews
                Glide.with(imageAvatar).load(anyObject.avatar).into(imageAvatar)
                Glide.with(imageItem).load(anyObject.url).into(imageItem)
            }
        }

        override fun onClick(action: () -> Unit, baseActionListener: BaseActionListener) {
            with(bind) {
                if (baseActionListener == BaseActionListener.ADD) {
                    importantImageView.setOnClickListener {
                        action()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
        holder.onClick({ addFavorite(item!!) }, BaseActionListener.ADD)
    }

    private fun addFavorite(image: Image) {
        image.let { importantListener?.invoke(it) }
    }
}
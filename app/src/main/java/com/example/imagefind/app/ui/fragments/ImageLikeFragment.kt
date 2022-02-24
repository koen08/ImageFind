package com.example.imagefind.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.R
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.FavoriteViewModel
import com.example.imagefind.app.ui.FavoriteViewModelFactory
import com.example.imagefind.app.ui.adapters.FavoriteListAdapter
import com.example.imagefind.domain.models.ImageFavoriteList
import javax.inject.Inject

class ImageLikeFragment : Fragment() {

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_like, container, false)

        recyclerView = view.findViewById(R.id.recycleImageLike)
        initRecyclerView()

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = GridLayoutManager(activity, 2)

        viewModel = ViewModelProvider(this, favoriteViewModelFactory)
            .get(FavoriteViewModel::class.java)

        viewModel.listImageLiveData.observe(viewLifecycleOwner, ::glideImageList)

        viewModel.getImageAll()

        return view
    }

    private fun glideImageList(imageFavoriteList: ImageFavoriteList) {
        val adapter = FavoriteListAdapter(imageFavoriteList.hits)
        recyclerView?.adapter = adapter
        /*adapter.importantListener = {
            viewModel.addImageIdToDB(it.id, it.url)
        }*/
    }

    private fun initRecyclerView() {
        val adapter = FavoriteListAdapter(ArrayList())
        recyclerView?.adapter = adapter
    }
}
package com.example.imagefind.app.ui.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.adapters.FavoriteListAdapter
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.databinding.FragmentImageLikeBinding
import com.example.imagefind.domain.models.ImageFavoriteList
import javax.inject.Inject

class ImageLikeFragment : Fragment() {

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: FavoriteViewModel

    private var _binding: FragmentImageLikeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageLikeBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recycleImageLike
        val adapter = FavoriteListAdapter()
        recyclerView?.adapter = adapter

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = GridLayoutManager(activity, 2)

        viewModel = ViewModelProvider(this, favoriteViewModelFactory)
            .get(FavoriteViewModel::class.java)

        viewModel.listImageLiveData.observe(viewLifecycleOwner, {
            glideImageList(it, adapter)
        })

        listenerDeleteImage(adapter)

        viewModel.getImageAll()

        return view
    }

    private fun listenerDeleteImage(adapter: FavoriteListAdapter) {
        adapter.importantListener = {
            val imageTable = ImageTable(it.id, it.url)
            viewModel.delete(imageTable)
        }
    }

    private fun glideImageList(imageFavoriteList: ImageFavoriteList, adapter: FavoriteListAdapter) {
        adapter.imageList = imageFavoriteList.hits
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
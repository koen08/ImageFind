package com.example.imagefind.app.ui.fragments.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.adapters.FavoriteListAdapter
import com.example.imagefind.app.ui.ViewModelFactory
import com.example.imagefind.app.ui.fragments.AbstractFragment
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.databinding.FragmentImageLikeBinding
import com.example.imagefind.domain.models.ImageFavorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ImageLikeFragment : AbstractFragment() {

    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: FavoriteViewModel

    private var _binding: FragmentImageLikeBinding? = null
    private val binding get() = _binding!!

    @ExperimentalCoroutinesApi
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

        viewModel = injectViewModel()

        viewModel.listImageLiveData.observe(viewLifecycleOwner) {
            glideImageList(it, adapter)
            Log.d("DEBUG_DATA", "item : ${adapter.snapshot().size}")
        }

        listenerDeleteImage(adapter)

        viewModel.getImageAll()

        return view
    }

    private fun listenerDeleteImage(adapter: FavoriteListAdapter) {
        adapter.favoriteListener.listener = {
            val imageTable = ImageTable(it.id, it.url)
            viewModel.delete(imageTable)
        }
    }

    private fun glideImageList(
        imageFavoriteList: PagingData<ImageFavorite>,
        adapter: FavoriteListAdapter
    ) {
        adapter.submitData(lifecycle, imageFavoriteList)
    }

    override fun onDestroy() {
        if (::viewModel.isInitialized) {
            viewModel.onDestroy()
        }
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
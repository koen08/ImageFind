package com.example.imagefind.app.ui.fragments.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.adapters.FavoriteListAdapter
import com.example.imagefind.data.database.models.ImageTable
import com.example.imagefind.databinding.FragmentImageLikeBinding
import com.example.imagefind.domain.models.ImageFavorite
import com.example.imagefind.domain.models.ImageFavoriteList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ImageLikeFragment : Fragment() {

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
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

        viewModel = ViewModelProvider(this, favoriteViewModelFactory)
            .get(FavoriteViewModel::class.java)

        viewModel.listImageLiveData.observe(viewLifecycleOwner) {
            glideImageList(it, adapter)
            Log.d("DEBUG_DATA", "item : ${adapter.snapshot().size}")
        }

        listenerDeleteImage(adapter)

        viewModel.getImageAll()

        return view
    }

    private fun listenerDeleteImage(adapter: FavoriteListAdapter) {
        adapter.favoriteListener.deleteListener = {
            val imageTable = ImageTable(it.id, it.url)
            viewModel.delete(imageTable)
        }
    }

    private fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    private fun glideImageList(
        imageFavoriteList: PagingData<ImageFavorite>,
        adapter: FavoriteListAdapter
    ) {
      //  Log.d("DEBUG_DATA", "item : ${adapter.snapshot().items}")
        adapter.submitData(lifecycle, imageFavoriteList)
    //    Log.d("DEBUG_DATA", "item : ${adapter.snapshot().items}")
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
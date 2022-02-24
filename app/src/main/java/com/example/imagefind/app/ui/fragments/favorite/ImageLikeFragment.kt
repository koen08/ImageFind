package com.example.imagefind.app.ui.fragments.favorite

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
import com.example.imagefind.app.ui.adapters.FavoriteListAdapter
import com.example.imagefind.databinding.FavoriteListItemBinding
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
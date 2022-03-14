package com.example.imagefind.app.ui.fragments.wall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.adapters.ImageListAdapter
import com.example.imagefind.databinding.FragmentImageWallBinding
import com.example.imagefind.domain.models.Image
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ImageWallFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentImageWallBinding? = null
    private val binding get() = _binding!!

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageWallBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recycleImageWall
        val adapter = ImageListAdapter()
        recyclerView?.adapter = adapter

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.listImageLiveData.observe(viewLifecycleOwner) {
            glideImageList(it, adapter)
        }

        listenerAddImage(adapter)

        viewModel.getImageListByName()

        return view
    }

    private fun listenerAddImage(adapter: ImageListAdapter) {
        adapter.imageListener.addFavoriteImage = {
            viewModel.addImageIdToDB(it.id, it.url)
        }
        adapter.searchListener.headerListener = {
            viewModel.query = it
            viewModel.getImageListByName()
        }
    }

    private fun glideImageList(pagingData: PagingData<Image>, adapter: ImageListAdapter) {
        adapter.submitData(lifecycle, pagingData)
    }

    override fun onDestroy() {
        Log.i("QQQ", "OnDestroy")
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun onDestroyView() {
        Log.i("QQQ", "onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}
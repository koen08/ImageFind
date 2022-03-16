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
import com.example.imagefind.app.ui.MainActivity
import com.example.imagefind.app.ui.adapters.ImageListAdapter
import com.example.imagefind.app.ui.fragments.advanceQuery.AdvanceQueryFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("QQQ", "onViewCreated")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("QQQ", "onViewCreated")
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("QQQ", "onCreateView")
        val bundle = arguments
        val orientation = bundle?.getString("orientation")
        val imageType = bundle?.getString("imageType")
        val order = bundle?.getString("order")

        _binding = FragmentImageWallBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recycleImageWall
        val adapter = ImageListAdapter()
        recyclerView?.adapter = adapter

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel._orientationLiveData.value = "all"

        if (orientation != null) {
            viewModel._orientationLiveData.value = orientation
        }
        if (imageType != null) {
            viewModel.imageType = imageType
        }
        if (order != null) {
            viewModel.order = order
        }

        viewModel.listImageLiveData.observe(viewLifecycleOwner) {
            glideImageList(it, adapter)
        }

        listenerAddImage(adapter)

        viewModel.getImageListByName()

        return view
    }

    private fun listenerAddImage(adapter: ImageListAdapter) {
        adapter.imageListener.listener = {
            viewModel.addImageIdToDB(it.id, it.url)
        }
        adapter.searchListener.listener = {
            viewModel.query = it
            viewModel.getImageListByName()
        }
        adapter.advanceSearchListener.listener = {
            (activity as MainActivity).makeCurrentFragment(AdvanceQueryFragment())
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
        Log.i("QQQ", viewModel._orientationLiveData.value.toString())
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("QQQ", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }
}
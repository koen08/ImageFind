package com.example.imagefind.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.R
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.MainViewModel
import com.example.imagefind.app.ui.ViewModelFactory
import com.example.imagefind.app.ui.adapters.ImageListAdapter
import com.example.imagefind.domain.models.ImageDto
import javax.inject.Inject

class ImageWallFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_wall, container, false)

        recyclerView = view.findViewById(R.id.recycleImageWall)
        initRecyclerView()

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.listImageLiveData.observe(viewLifecycleOwner, ::glideImageList)

        viewModel.getImageListByName("android")

        return view
    }

    private fun glideImageList(imageList: List<ImageDto>) {
        val adapter = ImageListAdapter(imageList)
        recyclerView?.adapter = adapter
        adapter.importantListener = {
            viewModel.addImageIdToDB(it)
        }
    }

    private fun initRecyclerView() {
        val adapter = ImageListAdapter(ArrayList())
        recyclerView?.adapter = adapter
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}
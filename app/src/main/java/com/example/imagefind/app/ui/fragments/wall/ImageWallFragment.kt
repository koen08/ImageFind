package com.example.imagefind.app.ui.fragments.wall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.R
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.adapters.ImageListAdapter
import com.example.imagefind.data.network.models.ImageNet
import com.example.imagefind.databinding.FragmentImageWallBinding
import com.example.imagefind.domain.models.Image
import javax.inject.Inject

class ImageWallFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentImageWallBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageWallBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recycleImageWall
        initRecyclerView()

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.listImageLiveData.observe(viewLifecycleOwner, ::glideImageList)
        viewModel.completeAddInfoImage.observe(viewLifecycleOwner, ::showToast)

        viewModel.getImageListByName("dog")

        return view
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun glideImageList(pagingData: PagingData<ImageNet>) {
        val adapter = ImageListAdapter()
        adapter.submitData(lifecycle, pagingData)
        recyclerView?.adapter = adapter
        adapter.importantListener = {
            viewModel.addImageIdToDB(it.id, it.url)
        }
    }

    private fun initRecyclerView() {
        val adapter = ImageListAdapter()
        recyclerView?.adapter = adapter
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
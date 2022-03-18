package com.example.imagefind.app.ui.fragments.wall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagefind.app.App
import com.example.imagefind.app.ui.MainActivity
import com.example.imagefind.app.ui.ViewModelFactory
import com.example.imagefind.app.ui.adapters.ImageListAdapter
import com.example.imagefind.app.ui.fragments.AbstractFragment
import com.example.imagefind.app.ui.fragments.advanceQuery.AdvanceQueryFragment
import com.example.imagefind.databinding.FragmentImageWallBinding
import com.example.imagefind.domain.models.Image
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ImageWallFragment : AbstractFragment() {

    var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentImageWallBinding? = null
    private val binding get() = _binding!!

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        val query = bundle?.getString("query")
        val orientation = bundle?.getString("orientation")
        val imageType = bundle?.getString("imageType")
        val order = bundle?.getString("order")

        _binding = FragmentImageWallBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recycleImageWall
        val adapter = ImageListAdapter(
            {
                viewModel.addImageIdToDB(it.id, it.url)
            },
            {
                viewModel.getImageListByName(query, "", "", "")
            },
            {
                (activity as MainActivity).makeCurrentFragment(AdvanceQueryFragment(), true)
            }
        )
        recyclerView?.adapter = adapter

        (activity?.application as App).appComponent.inject(this)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = injectViewModel()

        viewModel.listImageLiveData.observe(viewLifecycleOwner) {
            glideImageList(it, adapter)
        }

        viewModel.getImageListByName(query, orientation, imageType, order)

        return view
    }

    private fun glideImageList(pagingData: PagingData<Image>, adapter: ImageListAdapter) {
        adapter.submitData(lifecycle, pagingData)
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
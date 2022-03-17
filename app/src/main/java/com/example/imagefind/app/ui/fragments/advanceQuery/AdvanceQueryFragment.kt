package com.example.imagefind.app.ui.fragments.advanceQuery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imagefind.R
import com.example.imagefind.app.ui.MainActivity
import com.example.imagefind.app.ui.fragments.wall.ImageWallFragment
import com.example.imagefind.databinding.FragmentAdvanceQueryBinding
import com.google.android.material.chip.Chip

class AdvanceQueryFragment : Fragment() {

    private var _binding: FragmentAdvanceQueryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdvanceQueryBinding.inflate(inflater, container, false)

        val orientationChipGroup = binding.orientationChipGroup
        val imageTypeChipGroup = binding.imageTypeChipGroup
        val orderChipGroup = binding.orderChipGroup
        val queryEditText = binding.searchEditText

        var chipOrientationChecked: Chip = getChip(orientationChipGroup.checkedChipId)
        var chipImageTypeChecked: Chip = getChip(imageTypeChipGroup.checkedChipId)
        var chipOrderChecked: Chip = getChip(orderChipGroup.checkedChipId)

        var closeButton = binding.closeButton

        closeButton.setOnClickListener {
            activity?.onBackPressed()
        }

        orientationChipGroup.setOnCheckedChangeListener { _, id ->
            chipOrientationChecked = getChip(id)
        }

        imageTypeChipGroup.setOnCheckedChangeListener { _, id ->
            chipImageTypeChecked = getChip(id)
        }

        orderChipGroup.setOnCheckedChangeListener { _, id ->
            chipOrderChecked = getChip(id)
        }

        val searchButton = binding.searchButton

        searchButton.setOnClickListener {
            val imageWallFragment = ImageWallFragment()
            val bundle = getBundle(
                queryEditText.text.toString(),
                chipOrientationChecked.text.toString(),
                chipImageTypeChecked.text.toString(),
                chipOrderChecked.text.toString()
            )
            imageWallFragment.arguments = bundle
            (activity as MainActivity).makeCurrentFragment(imageWallFragment, false)
        }

        return binding.root
    }

    private fun getBundle(
        query: String,
        orientation: String,
        imageType: String,
        order: String
    ): Bundle {
        val bundle = Bundle()
        bundle.putString("query", query)
        bundle.putString("orientation", orientation.lowercase())
        bundle.putString("imageType", imageType.lowercase())
        bundle.putString("order", order.lowercase())
        return bundle
    }

    private fun getChip(id: Int): Chip {
        return binding.root.findViewById(id)
    }

}
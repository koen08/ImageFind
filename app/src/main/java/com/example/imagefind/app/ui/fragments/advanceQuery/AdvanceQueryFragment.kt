package com.example.imagefind.app.ui.fragments.advanceQuery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imagefind.databinding.FragmentAdvanceQueryBinding

class AdvanceQueryFragment : Fragment() {

    private var _binding: FragmentAdvanceQueryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdvanceQueryBinding.inflate(inflater, container, false)
        return binding.root
    }

}
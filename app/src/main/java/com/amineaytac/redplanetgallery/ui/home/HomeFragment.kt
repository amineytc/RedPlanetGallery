package com.amineaytac.redplanetgallery.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeVM: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVM.getMarsItems
        getMarsItems()
    }

    private fun getMarsItems() {
        homeVM.getMarsItems.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Log.d("mydata", it.data.toString())
                }

                is Resource.Loading -> {
                    Log.d("mydata", "it.data.toString()")
                }

                is Resource.Error -> {
                    Log.d("mydata", "")
                }
                else -> {}
            }
        }
    }
}
package com.amineaytac.redplanetgallery.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amineaytac.redplanetgallery.databinding.FragmentFavoriteBinding
import com.amineaytac.redplanetgallery.util.gone
import com.amineaytac.redplanetgallery.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy {
        FavoriteAdapter { id ->
            favoriteViewModel.deleteItemFavorite(id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeFavoriteData()
    }

    private fun setupRecyclerView() = with(binding) {
        favoriteRcyc.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeFavoriteData() {
        favoriteViewModel.getMarsItems.observe(viewLifecycleOwner) { favoriteList ->
            if (favoriteList.isNullOrEmpty()) {
                binding.favoriteRcyc.gone()
            } else {
                binding.favoriteRcyc.visible()
                favoriteAdapter.submitList(favoriteList)
            }
        }
    }
}
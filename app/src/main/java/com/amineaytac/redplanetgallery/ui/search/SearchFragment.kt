package com.amineaytac.redplanetgallery.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.core.network.dto.Photo
import com.amineaytac.redplanetgallery.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter(emptyList())
        observeItems()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if ((newText?.length ?: 0) >= 3) {
                    searchViewModel.searchMarsItems(newText.orEmpty())
                } else {
                    searchAdapter.setList(emptyList())
                }
                return true
            }
        })
    }

    private fun initAdapter(list: List<Photo>) {
        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearch.adapter = searchAdapter
        searchAdapter.setList(list)
    }

    private fun observeItems() {
        searchViewModel.searchMarsItems.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { it1 -> searchAdapter.setList(it1) }
                }

                is Resource.Loading -> {}
                is Resource.Error -> {}
            }
        }
    }
}
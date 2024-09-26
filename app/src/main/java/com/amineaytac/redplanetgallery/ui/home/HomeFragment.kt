package com.amineaytac.redplanetgallery.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.amineaytac.redplanetgallery.R
import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.core.data.model.FavoriteModel
import com.amineaytac.redplanetgallery.core.network.dto.Photo
import com.amineaytac.redplanetgallery.databinding.FragmentHomeBinding
import com.amineaytac.redplanetgallery.databinding.ItemDialogBinding
import com.amineaytac.redplanetgallery.ui.favorite.FavoriteViewModel
import com.amineaytac.redplanetgallery.util.gone
import com.amineaytac.redplanetgallery.util.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter = HomeAdapter { item -> showItemDialog(item) }
    private val homeViewModel: HomeViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getMarsItems
        getMarsItems()

        binding.floatingButton.setOnClickListener {
            binding.homeRcyc.scrollToPosition(0) }
    }

    private fun setHomeMarsRecyc(marsList: List<Photo>) {
        binding.homeRcyc.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            homeAdapter.setList(marsList)
            adapter = homeAdapter
        }
    }

    private fun showItemDialog(item: Photo) {

        val dialogBinding = ItemDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogBinding.root)

        homeViewModel.isItemFavorited(item.id.toString())
            .observe(viewLifecycleOwner) { isFavorited ->
                if (isFavorited) {
                    dialogBinding.favoriteImageView.setImageResource(R.drawable.ic_fill_favorite)
                } else {
                    dialogBinding.favoriteImageView.setImageResource(R.drawable.ic_empty_favorite)
                }
            }

        Glide.with(requireContext())
            .load(item.imgSrc)
            .into(dialogBinding.dialogImage)

        dialogBinding.dialogText.text = item.camera?.name
        dialogBinding.dialogText2.text = item.camera?.fullName

        dialogBinding.favoriteImageView.setOnClickListener {

            val favoriteItem = FavoriteModel(
                id = item.id,
                name = item.camera?.name.orEmpty(),
                fullName = item.camera?.fullName.orEmpty(),
                imgSrc = item.imgSrc
            )
            favoriteViewModel.addItemFavorite(favoriteItem)
        }
        dialogBuilder.create().show()
    }

    private fun getMarsItems() {
        homeViewModel.getMarsItems.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visible()
                    binding.homeRcyc.gone()
                }

                is Resource.Error -> {
                    binding.progressBar.visible()
                    binding.homeRcyc.gone()
                }

                else -> {
                    binding.progressBar.gone()
                    binding.homeRcyc.visible()
                    it.data.let {
                        setHomeMarsRecyc(it!!)
                    }
                }
            }
        }
    }
}
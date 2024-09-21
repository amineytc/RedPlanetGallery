package com.amineaytac.redplanetgallery.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.amineaytac.redplanetgallery.R
import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.core.network.dto.Photo
import com.amineaytac.redplanetgallery.databinding.FragmentHomeBinding
import com.amineaytac.redplanetgallery.util.gone
import com.amineaytac.redplanetgallery.util.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter = HomeAdapter { item -> showItemDialog(item) }
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

        binding.floatingButton.setOnClickListener {
            binding.homeRcyc.scrollToPosition(0)
        }
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

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.item_dialog, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)

        val dialogImage = dialogView.findViewById<ImageView>(R.id.dialogImage)
        val dialogText = dialogView.findViewById<TextView>(R.id.dialogText)
        val dialogText2 = dialogView.findViewById<TextView>(R.id.dialogText2)

        Glide.with(requireContext())
            .load(item.imgSrc)
            .into(dialogImage)

        dialogText.text = item.camera?.name
        dialogText2.text = item.camera?.fullName

        dialogBuilder.create().show()
    }

    private fun getMarsItems() {
        homeVM.getMarsItems.observe(viewLifecycleOwner) {
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
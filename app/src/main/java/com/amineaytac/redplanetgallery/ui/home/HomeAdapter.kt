package com.amineaytac.redplanetgallery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.redplanetgallery.core.network.dto.Photo
import com.amineaytac.redplanetgallery.databinding.ItemMarsBinding
import com.bumptech.glide.Glide

class HomeAdapter(
    private val onItemClick: (Photo) -> Unit
) : RecyclerView.Adapter<HomeAdapter.MarsViewHolder>() {

    private val marsList = mutableListOf<Photo>()

    fun setList(list: List<Photo>) {
        marsList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class MarsViewHolder(
        private val binding: ItemMarsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo, position: Int) = with(binding) {

            marsText.text = item.camera?.name
            marsFullText.text = item.camera?.fullName

            Glide.with(marsImage.context)
                .load(item.imgSrc)
                .into(marsImage)

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val binding = ItemMarsBinding.inflate(LayoutInflater.from(parent.context))
        return MarsViewHolder(binding)
    }

    override fun getItemCount(): Int = marsList.size

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        val item = marsList[position]
        item.let {
            holder.bind(item, position)
        }
    }
}
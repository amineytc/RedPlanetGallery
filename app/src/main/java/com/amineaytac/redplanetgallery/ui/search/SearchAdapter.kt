package com.amineaytac.redplanetgallery.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.redplanetgallery.core.network.dto.Photo
import com.amineaytac.redplanetgallery.databinding.ItemSearchBinding
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val searchList = mutableListOf<Photo>()
    fun setList(list: List<Photo>) {
        searchList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
    inner class ViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo) = with(binding) {

            Glide.with(marsSearchImage.context)
                .load(item.imgSrc)
                .into(marsSearchImage)

            marsSearchName.text = item.camera?.name
            marsSearchFullName.text = item.camera?.fullName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = searchList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchList[position]
        holder.bind(item)
    }
}
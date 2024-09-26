package com.amineaytac.redplanetgallery.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.redplanetgallery.core.data.model.FavoriteModel
import com.amineaytac.redplanetgallery.databinding.ItemFavoriteBinding
import com.bumptech.glide.Glide
class FavoriteAdapter(
    var onDeleteClick: (Int) -> Unit = {}
) : ListAdapter<FavoriteModel, FavoriteAdapter.ViewHolder>(DiffCallBack()) {
    inner class ViewHolder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteModel) = with(binding) {

            marsFavoriteName.text = item.name
            marsFavoriteFullName.text = item.fullName

            Glide.with(marsFavoriteImage.context)
                .load(item.imgSrc)
                .into(marsFavoriteImage)

            marsFavoriteDelete.setOnClickListener {
                item.id?.let { onDeleteClick(it) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class DiffCallBack : DiffUtil.ItemCallback<FavoriteModel>() {
        override fun areItemsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem == newItem
        }
    }
}
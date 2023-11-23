package com.example.bosta_task.presentation.view.album.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bosta_task.R
import com.example.bosta_task.databinding.PhotoListItemBinding
import com.example.bosta_task.presentation.model.PhotoUiDto

class PhotosAdapter(val context: Context,private val onClick: (PhotoUiDto) -> Unit) :
    ListAdapter<PhotoUiDto, PhotosAdapter.PhotoHolder>(RecyclerDiffUtilPhotoUiDto()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PhotoListItemBinding.inflate(inflater, parent, false)
        return PhotoHolder(binding)
    }


    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
    }

    inner class PhotoHolder(private val binding: PhotoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: PhotoUiDto) {
            Glide.with(context)
                .load(currentItem.thumbnailUrl)
                .placeholder(R.drawable.reload)
                .error(R.drawable.reload)
                .into(binding.ivPhoto)
            binding.ivPhoto.setOnClickListener {
                onClick(currentItem)
            }
        }
    }
}

class RecyclerDiffUtilPhotoUiDto : DiffUtil.ItemCallback<PhotoUiDto>() {
    override fun areItemsTheSame(oldItem: PhotoUiDto, newItem: PhotoUiDto): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PhotoUiDto, newItem: PhotoUiDto): Boolean {
        return oldItem == newItem
    }
}
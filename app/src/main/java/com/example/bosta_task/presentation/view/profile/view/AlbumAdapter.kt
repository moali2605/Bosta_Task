package com.example.bosta_task.presentation.view.profile.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bosta_task.databinding.AlbumListItemBinding
import com.example.bosta_task.presentation.model.album.AlbumUiDto

class AlbumAdapter(private val onClick: (AlbumUiDto) -> Unit) : ListAdapter<AlbumUiDto, AlbumAdapter.AlbumHolder>(RecyclerDiffUtilAlbumUiDto()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = AlbumListItemBinding.inflate(inflater, parent, false)
        return AlbumHolder(binding)
    }


    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
    }

    inner class AlbumHolder(private val binding: AlbumListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: AlbumUiDto) {
            binding.tvAlbumName.text=currentItem.title
            binding.cvAlbum.setOnClickListener {
                onClick(currentItem)
            }
        }
    }
}

class RecyclerDiffUtilAlbumUiDto : DiffUtil.ItemCallback<AlbumUiDto>() {
    override fun areItemsTheSame(oldItem: AlbumUiDto, newItem: AlbumUiDto): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: AlbumUiDto, newItem: AlbumUiDto): Boolean {
        return oldItem == newItem
    }
}
package com.example.mazika.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.mazika.data.entities.Song
import com.example.mazika.databinding.SwipeItemBinding

class SwipeSongAdapter : BaseSongAdapter<SwipeItemBinding>(SwipeItemBinding::inflate) {

    override val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.binding.apply {
            val text = "${song.title} - ${song.subtitle}"
            tvPrimary.text = text
            tvPrimary.isSelected = true

            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }
}








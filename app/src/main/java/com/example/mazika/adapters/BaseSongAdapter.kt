package com.example.mazika.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mazika.data.entities.Song


abstract class BaseSongAdapter<T : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> T
) : RecyclerView.Adapter<BaseSongAdapter<T>.SongViewHolder>() {

    inner class SongViewHolder(val binding: T) : RecyclerView.ViewHolder(binding.root)

    protected abstract val differ: AsyncListDiffer<Song>

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int = songs.size

    protected abstract val diffCallback: DiffUtil.ItemCallback<Song>
}


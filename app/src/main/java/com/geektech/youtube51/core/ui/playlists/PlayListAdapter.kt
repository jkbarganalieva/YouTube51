package com.geektech.youtube51.core.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.youtube51.databinding.ItemsBinding
import com.geektech.youtube51.loadImage
import com.geektech.youtube51.remote.model.Item

class PlayListAdapter(val onClick: (Item) -> Unit) :
    RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")

    private var list = arrayListOf<Item>()

    fun setList(liste: List<Item>) {
        this.list = liste as ArrayList<Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        return PlayListViewHolder(
            ItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {

            binding.ivVideo.loadImage(item.snippet.thumbnails.default.url)
            binding.tvTitle.text = item.snippet.title
            binding.tvDesc.text = item.snippet.description
            binding.itemCinema.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}







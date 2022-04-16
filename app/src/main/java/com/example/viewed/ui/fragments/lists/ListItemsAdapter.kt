package com.example.viewed.ui.fragments.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewed.R
import com.example.viewed.api.items.MoviePage.Info
import com.example.viewed.databinding.ItemListMovieBinding
import com.example.viewed.ui.fragments.lists.ListItemsAdapter.ListItemsViewHolder

class ListItemsAdapter(private val films: ArrayList<Info>) : RecyclerView.Adapter<ListItemsViewHolder>() {
    inner class ListItemsViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/${info.poster_path}")
                    .error(R.drawable.ic_launcher_background)
                    .into(ivMoviePoster)

                tvTitle.text = info.title
                tvYear.text = info.release_date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemsViewHolder {
        val binding = ItemListMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemsViewHolder, position: Int) {
        val currentItem = films[position]
        if (this.itemCount != 0) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

}

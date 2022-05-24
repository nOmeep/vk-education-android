package com.example.viewed.ui.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.viewed.R
import com.example.viewed.api.items.MoviePage.Info
import com.example.viewed.databinding.ItemSearchResultMovieBinding
import com.example.viewed.ui.fragments.search.SearchedItemsAdapter.SearchedItemsViewHolder

class SearchedItemsAdapter : PagingDataAdapter<Info, SearchedItemsViewHolder>(DIFF_UTIL) {
    inner class SearchedItemsViewHolder(private val binding: ItemSearchResultMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/${info.poster_path}")
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .error(R.drawable.no_poster)
                    .into(ivMoviePoster)

                tvTitle.text = info.title
                val data: String = info.release_date
                val year: String = if (data.length > 4) data.substring(0, 4) else data
                tvYear.text = year
                tvDescription.text = info.overview
                tvRating.text = info.vote_average.toString()
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Info>() {
            override fun areItemsTheSame(
                oldItem: Info,
                newItem: Info
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Info,
                newItem: Info
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedItemsViewHolder {
        val binding =
            ItemSearchResultMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchedItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchedItemsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}

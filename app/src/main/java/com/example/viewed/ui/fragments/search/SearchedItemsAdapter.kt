package com.example.viewed.ui.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewed.api.items.MoviePage.Info
import com.example.viewed.databinding.ItemSearchResultMovieBinding
import com.example.viewed.ui.fragments.search.SearchedItemsAdapter.SearchedItemsViewHolder

class SearchedItemsAdapter : RecyclerView.Adapter<SearchedItemsViewHolder>() {
    inner class SearchedItemsViewHolder(private val binding: ItemSearchResultMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info) {
            binding.apply {
                tvTitle.text = info.title
                tvYear.text = info.release_date
            }
        }
    }

    private val asyncDiffer = AsyncListDiffer(this, DIFF_UTIL)

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

    fun submitList(list: List<Info>) {
        asyncDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedItemsViewHolder {
        val binding =
            ItemSearchResultMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchedItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchedItemsViewHolder, position: Int) {
        val currentItem = asyncDiffer.currentList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = asyncDiffer.currentList.size
}
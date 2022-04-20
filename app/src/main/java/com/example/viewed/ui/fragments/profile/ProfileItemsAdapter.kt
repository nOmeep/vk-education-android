package com.example.viewed.ui.fragments.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewed.R
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.databinding.ItemProfileResultMovieBinding

class ProfileItemsAdapter(private var deleteFunction: (id: Int) -> Unit) : RecyclerView.Adapter<ProfileItemsAdapter.ProfileItemViewHolder>() {
    private val differ = AsyncListDiffer(this, DIFF_UTIL)

    inner class ProfileItemViewHolder(private val binding: ItemProfileResultMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: SingleMovie) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/${info.poster_path}")
                    .error(R.drawable.ic_launcher_background)
                    .into(ivMoviePoster)

                tvTitle.text = info.title
                tvYear.text = info.release_date
                tvDelToObject.setOnClickListener {
                    deleteFunction.invoke(info.id)
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SingleMovie>() {
            override fun areItemsTheSame(
                oldItem: SingleMovie,
                newItem: SingleMovie
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: SingleMovie,
                newItem: SingleMovie
            ) = oldItem == newItem
        }
    }

    fun setDelete(delete: (id: Int) -> Unit) {
        deleteFunction = delete
    }

    fun submitData(list: List<SingleMovie>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val binding = ItemProfileResultMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) = holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size
}

package com.example.viewed.ui.fragments.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewed.R
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.databinding.ItemProfileResultMovieBinding

class ProfileItemsAdapter(private val dataSet: MutableList<SingleMovie>) : RecyclerView.Adapter<ProfileItemsAdapter.ProfileItemViewHolder>() {
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
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val binding = ItemProfileResultMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) = holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size
}

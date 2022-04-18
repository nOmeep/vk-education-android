package com.example.viewed.ui.fragments.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewed.R
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.databinding.ItemProfileResultMovieBinding
import kotlin.reflect.KFunction1
import kotlin.reflect.KSuspendFunction0

class ProfileItemsAdapter(
    private val dataSet: MutableList<SingleMovie>,
    private val del: KFunction1<Int, Unit>
) : RecyclerView.Adapter<ProfileItemsAdapter.ProfileItemViewHolder>() {
    inner class ProfileItemViewHolder(private val binding: ItemProfileResultMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(info: SingleMovie, position: Int) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/${info.poster_path}")
                    .error(R.drawable.ic_launcher_background)
                    .into(ivMoviePoster)

                tvTitle.text = info.title
                tvYear.text = info.release_date
                tvDelToObject.setOnClickListener {
                    del(info.id)
                    dataSet.removeAt(position)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val binding = ItemProfileResultMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) = holder.bind(dataSet[position], position)

    override fun getItemCount(): Int = dataSet.size
}

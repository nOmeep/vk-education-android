package com.example.viewed.screens.futures

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewed.MAIN
import com.example.viewed.R
import com.example.viewed.models.UpcomingItemModel
import kotlinx.android.synthetic.main.item_layout.view.*

class FutureAdapter : RecyclerView.Adapter<FutureAdapter.MainViewHolder>() {

    private var listMovies= emptyList<UpcomingItemModel>()

    class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent. context).inflate(
            R.layout.item_layout,parent,
            false)
        return MainViewHolder(view)

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.item_title.text=listMovies[position].title


        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${listMovies[position].poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemView.item_img)

    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setList (newlist: List<UpcomingItemModel>) {
        listMovies = newlist
        notifyDataSetChanged()
    }
    override fun onViewAttachedToWindow(holder: FutureAdapter.MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {


            FutureFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: FutureAdapter.MainViewHolder) {
        holder.itemView.setOnClickListener (null)
    }



}

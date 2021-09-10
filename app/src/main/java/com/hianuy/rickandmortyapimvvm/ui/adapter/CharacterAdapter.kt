package com.hianuy.rickandmortyapimvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hianuy.rickandmortyapimvvm.R
import com.hianuy.rickandmortyapimvvm.model.Result
import kotlinx.android.synthetic.main.item_list.view.*

class CharacterAdapter(
    private val moviesList: List<Result>
    )
    : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            movieTitle.text = moviesList[position].id.toString()
            movieImage.load(moviesList[position].image) {
                placeholder(R.drawable.ic_baseline_image_24)
                fallback(R.drawable.ic_baseline_image_24)
            }
        }
    }

    override fun getItemCount(): Int = moviesList.size




}
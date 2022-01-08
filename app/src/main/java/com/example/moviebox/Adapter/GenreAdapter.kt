package com.example.moviebox.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebox.ModelClass.Genre
import com.example.moviebox.databinding.CardviewGenreListBinding

class GenreAdapter(
    private val context: Context,
    private val genreList: List<Genre>,
    private val onGenreClicKListener: OnGenreClickListener,
    private val type: String
) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CardviewGenreListBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ), onGenreClicKListener
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genreList[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }


    inner class ViewHolder(
        private val binding: CardviewGenreListBinding,
        private val onGenreClickListener: OnGenreClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(genre: Genre) {
            binding.apply {
                tvGenre.text = genre.name
                root.setOnClickListener(this@ViewHolder)
            }
        }

        override fun onClick(p0: View?) {
            onGenreClickListener.onGenreClick(position, type)
        }
    }


    interface OnGenreClickListener {
        fun onGenreClick(position: Int, type: String)
    }
}
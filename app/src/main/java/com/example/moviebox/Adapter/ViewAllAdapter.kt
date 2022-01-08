package com.example.moviebox.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.Network.Url
import com.example.moviebox.R
import com.example.moviebox.databinding.CardviewViewAllItemBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator

class ViewAllAdapter(
    private val context: Context,
    private val isMovie: Boolean
) : PagingDataAdapter<MovieModel, ViewAllAdapter.ViewAllViewHolder>(COMPARATOR) {

    private lateinit var comunicator: Communicator

    override fun onBindViewHolder(holder: ViewAllAdapter.ViewAllViewHolder, position: Int) {

        val movie = getItem(position)
        holder.bindUi(movie!!)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewAllAdapter.ViewAllViewHolder {
        return ViewAllViewHolder(
            CardviewViewAllItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    inner class ViewAllViewHolder(private val binding: CardviewViewAllItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bindUi(movie: MovieModel) {

            val posterUrl: String = Url.imageBaseUrl + movie.moviePoster

            Glide.with(context)
                .load(posterUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)

            binding.root.setOnClickListener {
                if (isMovie) {
                    comunicator = context as Communicator
                    comunicator.passData(movie.movieId)
                } else {
                    comunicator = context as Communicator
                    comunicator.passShowData(movie.movieId)
                }
            }

        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) =
                oldItem == newItem
        }
    }


}
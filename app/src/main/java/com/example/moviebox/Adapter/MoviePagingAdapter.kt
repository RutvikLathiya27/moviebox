package com.example.moviebox.Adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.R
import com.example.moviebox.databinding.CardviewNormalsizeposterBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator

class MoviePagingAdapter(private val context: Context, private val isMovie: Boolean) :
    PagingDataAdapter<MovieModel, MoviePagingAdapter.MovieViewHolder>(COMPARATOR) {

    private lateinit var comunicator: Communicator


    var duration: Long = 500
    private var onAttach = true

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie!!)

        setAnimation(holder.itemView, position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = CardviewNormalsizeposterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(private val binding: CardviewNormalsizeposterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieModel) {
            val posterUrl: String =
                com.example.moviebox.Network.Url.imageBaseUrl + movie.moviePoster
            Glide.with(context)
                .load(posterUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgPoster)

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

    private fun setAnimation(itemView: View, position: Int) {
        var i = position
        if (!onAttach) {
            i = -1
        }
        val isNotFirstItem = i == -1
        i++
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animator.startDelay = if (isNotFirstItem) duration / 2 else i * duration / 3
        animator.duration = 500
        animatorSet.play(animator)
        animator.start()
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem == newItem


        }
    }


}
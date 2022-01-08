package com.example.moviebox.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.Network.Url
import com.example.moviebox.R
import com.example.moviebox.databinding.CardviewSearchedshowsBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator
import com.example.moviebox.ui.home.Fragments.SearchFragments
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter(private val context: Context, private val isMovie: Boolean) :
    PagingDataAdapter<MovieModel,
            SearchAdapter.SearchViewModel>(COMPARATOR) {

    val simpleReleaseDateFormat = SimpleDateFormat("yyyy")

    private lateinit var comunicator: Communicator



    override fun onBindViewHolder(holder: SearchViewModel, position: Int) {

        val movie = getItem(position)
        holder.bindUi(movie!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewModel {

        return SearchViewModel(
            CardviewSearchedshowsBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    }

    inner class SearchViewModel(private val binding: CardviewSearchedshowsBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        val calendar = Calendar.getInstance()
//        calendar.set(
//        movie.releaseDate.split("-")[0].toInt(),
//        movie.releaseDate.split("-")[1].toInt(),
//        movie.releaseDate.split("-")[2].toInt()
//        )

        fun bindUi(movie: MovieModel) {

            binding.tvShowname.text = movie.movieTitle
//            binding.tvReleaseyear.text = simpleReleaseDateFormat.format(calendar.time)

            val posterUrl: String = Url.imageBaseUrl + movie.moviePoster

            Glide.with(context)
                .load(posterUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgPostersearch)

            binding.root.setOnClickListener {
                if (isMovie){
                    comunicator = context as Communicator
                    comunicator.passData(movie.movieId)
                }else{
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
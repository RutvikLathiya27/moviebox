package com.example.moviebox.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.Network.Url
import com.example.moviebox.R
import com.example.moviebox.databinding.SliderItemBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(
    private val context: Context,
    private var sliders: List<MovieModel>,
    private val isMovie: Boolean
) : SliderViewAdapter<SliderAdapter.ViewHolder>() {

    private lateinit var comunicator: Communicator


    override fun getCount(): Int {
        return sliders.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = SliderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        val slider = sliders[position]
        viewHolder?.bind(slider)
    }


    inner class ViewHolder(private val binding: SliderItemBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {

        fun bind(movie: MovieModel) {

            val posterurl: String = Url.imageBaseUrl + movie.moviePoster
            Glide.with(context)
                .load(posterurl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)

            binding.root.setOnClickListener {
                if (isMovie) {
                    comunicator = context as Communicator
                    comunicator.passData(movie.movieId)
                }else{
                    comunicator = context as Communicator
                    comunicator.passShowData(movie.movieId)

                }
            }
        }
    }

    fun setData(sliders: List<MovieModel>) {
        this.sliders = sliders
        notifyDataSetChanged()
    }

}
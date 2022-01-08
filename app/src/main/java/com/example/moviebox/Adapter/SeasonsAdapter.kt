package com.example.moviebox.Adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.ModelClass.ShowDetailModel
import com.example.moviebox.Network.Url
import com.example.moviebox.R
import com.example.moviebox.databinding.SeasonItemBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator

class SeasonsAdapter(
    private val context: Context,
    private val seasonList: List<ShowDetailModel.Season>
) : RecyclerView.Adapter<SeasonsAdapter.SeasonViewHolder>() {

    private var onAttach = true

    private lateinit var comunicator: Communicator


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
            SeasonItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasonList[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                onAttach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }


    inner class SeasonViewHolder(private val binding: SeasonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(season: ShowDetailModel.Season) {
            val posterUrl: String = Url.imageBaseUrl + season.posterPath

            binding.apply {
                Glide.with(context)
                    .load(posterUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

                seasonName.text = season.name
            }

        }
    }




}
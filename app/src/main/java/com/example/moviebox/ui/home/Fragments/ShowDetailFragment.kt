package com.example.moviebox.ui.home.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.Adapter.MoviePagingAdapter
import com.example.moviebox.Adapter.SeasonsAdapter
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.ModelClass.ShowDetailModel
import com.example.moviebox.Network.Url
import com.example.moviebox.R
import com.example.moviebox.Repository.ShowsRepository
import com.example.moviebox.databinding.FragmentShowDetailBinding
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModel
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModelFactory
import com.example.moviebox.ui.home.TrailerActivity
import java.text.SimpleDateFormat
import java.util.*


class ShowDetailFragment : Fragment() {

    private var _binding: FragmentShowDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var showViewModel: ShowViewModel

    private lateinit var seasonsAdapter: SeasonsAdapter

    private lateinit var similarShowAdapter: MoviePagingAdapter

    val simpleReleaseDateFormat = SimpleDateFormat("yyyy")


    var showId: String? = ""

    private lateinit var key: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowDetailBinding.inflate(inflater, container, false)

        similarShowAdapter = MoviePagingAdapter(requireContext(), false)

        showId = arguments?.getString("showId")

        val showsRepository = ShowsRepository()
        val viewModelFactory = ShowViewModelFactory(showsRepository)
        showViewModel = ViewModelProvider(this, viewModelFactory)[ShowViewModel::class.java]

        showViewModel.apply {
            readyTvShowDetails(showId)
            getShowDetails().observe(viewLifecycleOwner, {
                if (it != null) {
                    bindUi(it)
                    showData()
                    readyTrailer(it.id.toString())
                } else {
                    showError()
                }
                Log.d("ShowDetailFragment", it.name)
            })

            showViewModel.similarTvShow(showId).observe(viewLifecycleOwner) {
                similarShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                binding.rvSimilarshows.scheduleLayoutAnimation()
            }
        }
        return binding.root
    }

    private fun readyTrailer(id: String) {

        showViewModel.readyTvShowVideos(id)
        showViewModel.getTvShowVideos().observe(viewLifecycleOwner, {
            if(it != null && it.isNotEmpty()) {
                key = if(it.size == 1) {
                    it[0].key
                } else {
                    it[it.size - 1].key
                }
                binding.btnPlaytrailer.visibility = View.VISIBLE
            }
        })

    }

    private fun bindUi(it: ShowDetailModel) {

        seasonsAdapter = SeasonsAdapter(requireContext(), it.seasons)

        val calendar = Calendar.getInstance()
        if (it.lastRelease != null) {
            calendar.set(
                it.lastRelease.split("-")[0].toInt(),
                it.lastRelease.split("-")[1].toInt(),
                it.lastRelease.split("-")[2].toInt()
            )
            binding.tvReleaseyearshow.text = simpleReleaseDateFormat.format(calendar.time)

        }


        binding.apply {
            toolbar.title = it.name
            tvShowname.text = it.name
            tvFamousline.text = it.tagline
//                tvRunningtime.text = it.runtime
            tvImdbrating.text = it.rating.toString()
            tvShowescription.text = it.overview
            tvLanguage.text = it.originalLenguage
            tvTotalEpisode.text = it.numberOfEpisodes.toString()
            tvTotalSeason.text = it.numberOfSeasons.toString()

            tvFirstSeason.text = it.firstRelease
            tvLastseason.text = it.lastRelease

            val posterUrl: String = Url.imageBaseUrl + it.posterPath
            val backgroundPoster: String = Url.imageBaseUrl + it.backgroundPoster
            Glide.with(requireContext())
                .load(posterUrl)
                .placeholder(R.drawable.poster_reload_logo)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mainPoster)

            Glide.with(requireContext())
                .load(backgroundPoster)
                .placeholder(R.drawable.poster_reload_logo)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(backdropPath)

            rvSimilarshows.apply {
                setHasFixedSize(true)
                adapter = similarShowAdapter
            }

            rvSeasons.apply {
                setHasFixedSize(true)
                adapter = seasonsAdapter
            }

            btnPlaytrailer.setOnClickListener {

                val intent = Intent(requireContext(), TrailerActivity::class.java)
                intent.putExtra("ID", key)
                startActivity(intent)
            }


        }

        similarShowAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                llayout.isVisible = loadState.source.refresh is LoadState.NotLoading
                errorText.isVisible = loadState.source.refresh is LoadState.Error

                //empty view
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && similarShowAdapter.itemCount < 1) {
                    viewSimilar.isVisible = false
                    textSimilar.isVisible = false
                }
            }
        }
    }

    private fun showError() {
        binding.apply {
            errorText.isVisible = true
            llayout.isVisible = false
        }
    }

    private fun showData() {
        binding.apply {
            errorText.isVisible = false
            llayout.isVisible = true
            progressBar.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
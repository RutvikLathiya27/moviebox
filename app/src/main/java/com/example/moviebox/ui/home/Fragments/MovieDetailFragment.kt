package com.example.moviebox.ui.home.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviebox.Adapter.MoviePagingAdapter
import com.example.moviebox.ModelClass.MovieDetailModel
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.Network.Url
import com.example.moviebox.R
import com.example.moviebox.Repository.MoviesRepository
import com.example.moviebox.databinding.FragmentMovieDetailBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator
import com.example.moviebox.ui.home.MovieViewModel.MovieViewModelFactory
import com.example.moviebox.ui.home.MovieViewModel.MoviesViewModel
import com.example.moviebox.ui.home.TrailerActivity
import java.text.NumberFormat
import java.util.*

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieViewModel: MoviesViewModel

    private lateinit var similarMovieAdapter: MoviePagingAdapter

    private lateinit var communicator: Communicator

    var movieId: String? = ""

    private lateinit var key: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        similarMovieAdapter = MoviePagingAdapter(requireContext(), true)

        movieId = arguments?.getString("movieId")

        val moviesRepository = MoviesRepository()
        val viewModelFactory = MovieViewModelFactory(moviesRepository)
        movieViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]


        movieViewModel.apply {
            readyMovieDetails(movieId)
            getMovieDetails().observe(viewLifecycleOwner, {
                if (it != null) {
                    bindUi(it)
                    showData()
                    readyTrailer(it.id)
                } else {
                    showError()
                }
            })

            movieViewModel.similarmovie(movieId).observe(viewLifecycleOwner) {
                similarMovieAdapter.submitData(viewLifecycleOwner.lifecycle,
                    it as PagingData<MovieModel>
                )
                binding.rvSimilarmovie.scheduleLayoutAnimation()
            }

        }
        return binding.root
    }

    private fun readyTrailer(id: String) {

        movieViewModel.readyMovieVideo(id)
        movieViewModel.getMovieVideo().observe(viewLifecycleOwner,{
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


    private fun bindUi(movieDetail: MovieDetailModel) {

        binding.apply {
            toolbar.title = movieDetail.movieName
            tvMoviename.text = movieDetail.movieName
            tvFamousline.text = movieDetail.tagline
            tvReleaseyearshow.text = movieDetail.releaseDate
            tvRunningtime.text = movieDetail.runtime
            tvImdbrating.text = movieDetail.voteAverage
            tvMoviedescription.text = movieDetail.movieDescription

            val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
            tvBudget.text = formatCurrency.format(movieDetail.budget)
            tvRevenue.text = formatCurrency.format(movieDetail.revenue)

            val posterUrl: String = Url.imageBaseUrl + movieDetail.moviePoster
            val backgroundPoster: String = Url.imageBaseUrl + movieDetail.backgroundPoster
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

            rvSimilarmovie.apply {
                setHasFixedSize(true)
                adapter = similarMovieAdapter
            }

            btnPlaytrailer.setOnClickListener {
                val intent = Intent(requireContext(), TrailerActivity::class.java)
                intent.putExtra("ID", key)
                startActivity(intent)
               
            }


        }

        similarMovieAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                llayout.isVisible = loadState.source.refresh is LoadState.NotLoading
                errorText.isVisible = loadState.source.refresh is LoadState.Error

                //empty view
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && similarMovieAdapter.itemCount < 1) {
                    viewSimilar.isVisible = false
                    textSimilar.isVisible = false
                }
            }
        }
    }

    private fun showData() {
        binding.apply {
            errorText.isVisible = false
            llayout.isVisible = true
            progressBar.isVisible = false
        }
    }

    private fun showError() {
        binding.apply {
            errorText.isVisible = true
            llayout.isVisible = false
            progressBar.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
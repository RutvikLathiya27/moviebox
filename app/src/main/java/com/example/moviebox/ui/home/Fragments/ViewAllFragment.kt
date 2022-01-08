package com.example.moviebox.ui.home.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebox.Adapter.ViewAllAdapter
import com.example.moviebox.Pagination.MovieLoadStateAdapter
import com.example.moviebox.R
import com.example.moviebox.Repository.MoviesRepository
import com.example.moviebox.Repository.ShowsRepository
import com.example.moviebox.databinding.FragmentMoviesBinding
import com.example.moviebox.databinding.FragmentViewAllBinding
import com.example.moviebox.ui.home.MovieViewModel.MovieViewModelFactory
import com.example.moviebox.ui.home.MovieViewModel.MoviesViewModel
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModel
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModelFactory
import kotlinx.android.synthetic.main.fragment_view_all.*


class ViewAllFragment : Fragment() {

    private var _binding: FragmentViewAllBinding? = null
    private val binding get() = _binding!!

    var key: String? = ""

    private lateinit var viewAllAdapter: ViewAllAdapter

    private lateinit var movieViewModel: MoviesViewModel
    private lateinit var showViewModel: ShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewAllBinding.inflate(inflater, container, false)

        key = arguments?.getString("key")

        if (key == "TrendingMovie" || key == "ActionMovie" || key == "AnimationMovie" || key == "DramaMovie" ||
            key == "PopularMovie" || key == "ComedyMovie" || key == "CrimeMovie" || key == "TopRatedMovie" ||
            key == "FamilyMovie" || key == "DocumentryMovie" || key == "FantacyMovie" || key == "HorrorMovie" ||
            key == "RomanceMovie" || key == "ScifiMovie" || key == "WarMovie"){

            viewAllAdapter = ViewAllAdapter(requireContext(), true)
        }else{
            viewAllAdapter = ViewAllAdapter(requireContext(), false)
        }

        val moviesRepository = MoviesRepository()
        val viewModelFactory = MovieViewModelFactory(moviesRepository)
        movieViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        val showsRepository = ShowsRepository()
        val showViewModelfactory = ShowViewModelFactory(showsRepository)
        showViewModel = ViewModelProvider(this, showViewModelfactory)[ShowViewModel::class.java]

        bindUi(key)
        if (key == "TrendingMovie") {
            movieViewModel.trendingMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

        if (key == "ActionMovie") {
            movieViewModel.actionadventureMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "AnimationMovie") {
            movieViewModel.animationMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "DramaMovie") {
            movieViewModel.dramaMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "PopularMovie") {
            movieViewModel.popularMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "ComedyMovie") {
            movieViewModel.comedyMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "CrimeMovie") {
            movieViewModel.trendingMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "TopRatedMovie") {
            movieViewModel.topratedMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "FamilyMovie") {
            movieViewModel.familyMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "DocumentryMovie") {
            movieViewModel.documentryMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "FantacyMovie") {
            movieViewModel.fantacyMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "HorrorMovie") {
            movieViewModel.horrorMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "RomanceMovie") {
            movieViewModel.romanceMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "ScifiMovie") {
            movieViewModel.scifiMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "WarMovie") {
            movieViewModel.warMovies.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "PopularShow") {
            showViewModel.popularTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "ComedyShow") {
            showViewModel.comedyTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "AnimationShow") {
            showViewModel.animationTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "RealityShow") {
            showViewModel.realityTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "TopShow") {
            showViewModel.topratedTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "ActionShow") {
            showViewModel.actionadventureTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "CrimeShow") {
            showViewModel.crimeTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "DocumentryShow") {
            showViewModel.documentryTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "NewsShow") {
            showViewModel.newsTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "DramaShow") {
            showViewModel.dramaTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "FamilyShow") {
            showViewModel.familyTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "KidsShow") {
            showViewModel.kidsTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "MysteryShow") {
            showViewModel.mysteryTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (key == "ScifiShow") {
            showViewModel.scifiTvshows.observe(viewLifecycleOwner) {
                viewAllAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

        return binding.root
    }

    private fun bindUi(key: String?) {

        binding.toolbar.title = key


        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 3)
                adapter = viewAllAdapter.withLoadStateHeaderAndFooter(
                    header = MovieLoadStateAdapter { viewAllAdapter.retry() },
                    footer = MovieLoadStateAdapter { viewAllAdapter.retry() }
                )
            }
            retryButton.setOnClickListener {
                viewAllAdapter.retry()
            }
        }

        viewAllAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                errorText.isVisible = loadState.source.refresh is LoadState.Error
                retryButton.isVisible = loadState.source.refresh is LoadState.Error

                //empty view
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && viewAllAdapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    errorText.isVisible = true
                } else {
                    errorText.isVisible = false
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
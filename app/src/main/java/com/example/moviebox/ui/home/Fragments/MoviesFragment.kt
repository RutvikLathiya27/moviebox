package com.example.moviebox.ui.home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import com.example.moviebox.Adapter.MoviePagingAdapter
import com.example.moviebox.Adapter.SliderAdapter
import com.example.moviebox.Network.Api
import com.example.moviebox.Repository.MoviesRepository
import com.example.moviebox.databinding.FragmentMoviesBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator
import com.example.moviebox.ui.home.MovieViewModel.MovieViewModelFactory
import com.example.moviebox.ui.home.MovieViewModel.MoviesViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var slideAdapter: SliderAdapter
    private lateinit var trendingMovieAdapter: MoviePagingAdapter
    private lateinit var actionadventureMovieAdapter: MoviePagingAdapter
    private lateinit var animationMovieAdapter: MoviePagingAdapter
    private lateinit var dramaMovieAdapter: MoviePagingAdapter
    private lateinit var popularMovieAdapter: MoviePagingAdapter
    private lateinit var comedyMovieAdapter: MoviePagingAdapter
    private lateinit var crimeMovieAdapter: MoviePagingAdapter
    private lateinit var topratedMovieAdapter: MoviePagingAdapter
    private lateinit var familyMovieAdapter: MoviePagingAdapter
    private lateinit var documentryMovieAdapter: MoviePagingAdapter
    private lateinit var fantacyMovieAdapter: MoviePagingAdapter
    private lateinit var horrorMovieAdapter: MoviePagingAdapter
    private lateinit var romanceMovieAdapter: MoviePagingAdapter
    private lateinit var scifiMovieAdapter: MoviePagingAdapter
    private lateinit var warMovieAdapter: MoviePagingAdapter

    private lateinit var comunicator: Communicator


    private lateinit var movieViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        slideAdapter = SliderAdapter(requireContext(), ArrayList(), true)

        trendingMovieAdapter = MoviePagingAdapter(requireContext(), true)
        actionadventureMovieAdapter = MoviePagingAdapter(requireContext(), true)
        animationMovieAdapter = MoviePagingAdapter(requireContext(), true)
        dramaMovieAdapter = MoviePagingAdapter(requireContext(), true)
        popularMovieAdapter = MoviePagingAdapter(requireContext(), true)
        comedyMovieAdapter = MoviePagingAdapter(requireContext(), true)
        crimeMovieAdapter = MoviePagingAdapter(requireContext(), true)
        topratedMovieAdapter = MoviePagingAdapter(requireContext(), true)
        familyMovieAdapter = MoviePagingAdapter(requireContext(), true)
        documentryMovieAdapter = MoviePagingAdapter(requireContext(), true)
        fantacyMovieAdapter = MoviePagingAdapter(requireContext(), true)
        horrorMovieAdapter = MoviePagingAdapter(requireContext(), true)
        romanceMovieAdapter = MoviePagingAdapter(requireContext(), true)
        scifiMovieAdapter = MoviePagingAdapter(requireContext(), true)
        warMovieAdapter = MoviePagingAdapter(requireContext(), true)

        val moviesRepository = MoviesRepository()
        val viewModelFactory =MovieViewModelFactory(moviesRepository)
        movieViewModel =ViewModelProvider(this@MoviesFragment, viewModelFactory)[MoviesViewModel::class.java]

        bindUI()

        movieViewModel.apply {
            readyNowPlayingMovies()
            getNowPlayingMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    slideAdapter.setData(it.subList(0, 7))
                }
            })

            movieViewModel.trendingMovies.observe(viewLifecycleOwner) {
                trendingMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.actionadventureMovies.observe(viewLifecycleOwner) {
                actionadventureMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.animationMovies.observe(viewLifecycleOwner) {
                animationMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.dramaMovies.observe(viewLifecycleOwner) {
                dramaMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.popularMovies.observe(viewLifecycleOwner) {
                popularMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.comedyMovies.observe(viewLifecycleOwner) {
                comedyMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.crimeMovies.observe(viewLifecycleOwner) {
                crimeMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.topratedMovies.observe(viewLifecycleOwner) {
                topratedMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.familyMovies.observe(viewLifecycleOwner) {
                familyMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.documentryMovies.observe(viewLifecycleOwner) {
                documentryMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.fantacyMovies.observe(viewLifecycleOwner) {
                fantacyMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.horrorMovies.observe(viewLifecycleOwner) {
                horrorMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.romanceMovies.observe(viewLifecycleOwner) {
                romanceMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.scifiMovies.observe(viewLifecycleOwner) {
                scifiMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            movieViewModel.warMovies.observe(viewLifecycleOwner) {
                warMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

        }

        return binding.root
    }

    private fun bindUI() {
        binding.apply {
            imageSlider.apply {
                setIndicatorAnimation(IndicatorAnimationType.WORM)
                setSliderAdapter(slideAdapter)
                startAutoCycle()
            }

            rvTrendingweekmovie.apply {
                setHasFixedSize(true)
                adapter = trendingMovieAdapter
            }

            moreTredingMovie.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("TrendingMovie")
            }

            rvActionAdventure.apply {
                setHasFixedSize(true)
                adapter = actionadventureMovieAdapter
            }

            moreActionMovie.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("ActionMovie")
            }

            rvAnimation.apply {
                setHasFixedSize(true)
                adapter = animationMovieAdapter
            }

            moreAnimationMovie.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("AnimationMovie")
            }

            rvDramamovie.apply {
                setHasFixedSize(true)
                adapter = dramaMovieAdapter
            }

            moreDrama.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("DramaMovie")
            }

            rvPopularmovie.apply {
                setHasFixedSize(true)
                adapter = popularMovieAdapter
            }

            morePopular.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("PopularMovie")
            }

            rvComedy.apply {
                setHasFixedSize(true)
                adapter = comedyMovieAdapter
            }

            moreComedy.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("ComedyMovie")
            }

            rvCrime.apply {
                setHasFixedSize(true)
                adapter = crimeMovieAdapter
            }

            moreCrime.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("CrimeMovie")
            }

            rvToprated.apply {
                setHasFixedSize(true)
                adapter = topratedMovieAdapter
            }

            moreTopRated.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("TopRatedMovie")
            }

            rvFamily.apply {
                setHasFixedSize(true)
                adapter = familyMovieAdapter
            }

            moreFammily.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("FamilyMovie")
            }

            rvDocumentary.apply {
                setHasFixedSize(true)
                adapter = documentryMovieAdapter
            }

            moreDocumentry.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("DocumentryMovie")
            }

            rvFantacy.apply {
                setHasFixedSize(true)
                adapter = fantacyMovieAdapter
            }

            moreFantacy.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("FantacyMovie")
            }

            rvHorror.apply {
                setHasFixedSize(true)
                adapter = horrorMovieAdapter
            }

            moreHorror.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("HorrorMovie")
            }

            rvRomance.apply {
                setHasFixedSize(true)
                adapter = romanceMovieAdapter
            }

            moreRomance.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("RomanceMovie")
            }

            rvScifi.apply {
                setHasFixedSize(true)
                adapter = scifiMovieAdapter
            }

            moreScifi.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("ScifiMovie")
            }

            rvWar.apply {
                setHasFixedSize(true)
                adapter = warMovieAdapter
            }

            moreWar.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("WarMovie")
            }

            popularMovieAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    linearlayout.isVisible = loadState.source.refresh is LoadState.NotLoading
                    errorText.isVisible = loadState.source.refresh is LoadState.Error
                    retryButton.isVisible = loadState.source.refresh is LoadState.Error

                    //empty view
                    if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && popularMovieAdapter.itemCount < 1) {
                        linearlayout.isVisible = false
                        errorText.isVisible = true
                    } else {
                        errorText.isVisible = false
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
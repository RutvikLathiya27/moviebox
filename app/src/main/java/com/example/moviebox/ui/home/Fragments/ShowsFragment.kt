package com.example.moviebox.ui.home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import com.example.moviebox.Adapter.MoviePagingAdapter
import com.example.moviebox.Adapter.SliderAdapter
import com.example.moviebox.Repository.ShowsRepository
import com.example.moviebox.databinding.FragmentShowsBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModel
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModelFactory
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import kotlinx.android.synthetic.main.fragment_shows.*

class ShowsFragment : Fragment() {

    private var _binding: FragmentShowsBinding? = null
    private val binding get() = _binding!!

    private lateinit var slideAdapter: SliderAdapter
    private lateinit var popularTvShowAdapter: MoviePagingAdapter
    private lateinit var comedyTvShowAdapter: MoviePagingAdapter
    private lateinit var animationTvShowAdapter: MoviePagingAdapter
    private lateinit var realityTvShowAdapter: MoviePagingAdapter
    private lateinit var topratedTvShowAdapter: MoviePagingAdapter
    private lateinit var actionadventureTvShowAdapter: MoviePagingAdapter
    private lateinit var crimeTvShowAdapter: MoviePagingAdapter
    private lateinit var documentryTvShowAdapter: MoviePagingAdapter
    private lateinit var newsTvShowAdapter: MoviePagingAdapter
    private lateinit var dramaTvShowAdapter: MoviePagingAdapter
    private lateinit var familyTvShowAdapter: MoviePagingAdapter
    private lateinit var kidsTvShowAdapter: MoviePagingAdapter
    private lateinit var mysteryTvShowAdapter: MoviePagingAdapter
    private lateinit var scifiTvShowAdapter: MoviePagingAdapter

    private lateinit var showViewModel : ShowViewModel

    private lateinit var comunicator: Communicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowsBinding.inflate(inflater, container, false)

        slideAdapter = SliderAdapter(requireContext(), ArrayList(), false)
        popularTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        comedyTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        animationTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        realityTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        topratedTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        actionadventureTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        crimeTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        documentryTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        newsTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        dramaTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        familyTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        kidsTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        mysteryTvShowAdapter = MoviePagingAdapter(requireContext(), false)
        scifiTvShowAdapter = MoviePagingAdapter(requireContext(), false)

        val showsRepository = ShowsRepository()
        val viewModelFactory = ShowViewModelFactory(showsRepository)
        showViewModel = ViewModelProvider(this, viewModelFactory)[ShowViewModel::class.java]

        bindUi()

        showViewModel.apply {
            readyLatestTvshows()
            getLatestTvshows().observe(viewLifecycleOwner, {
                if (it != null) {
                    slideAdapter.setData(it.subList(0, 7))
                }
            })

            showViewModel.popularTvshows.observe(viewLifecycleOwner) {
                popularTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.comedyTvshows.observe(viewLifecycleOwner) {
                comedyTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.animationTvshows.observe(viewLifecycleOwner) {
                animationTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.topratedTvshows.observe(viewLifecycleOwner) {
                topratedTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.realityTvshows.observe(viewLifecycleOwner) {
                realityTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.actionadventureTvshows.observe(viewLifecycleOwner) {
                actionadventureTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.crimeTvshows.observe(viewLifecycleOwner) {
                crimeTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.documentryTvshows.observe(viewLifecycleOwner) {
                documentryTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.newsTvshows.observe(viewLifecycleOwner) {
                newsTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.dramaTvshows.observe(viewLifecycleOwner) {
                dramaTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.familyTvshows.observe(viewLifecycleOwner) {
                familyTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.kidsTvshows.observe(viewLifecycleOwner) {
                kidsTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.mysteryTvshows.observe(viewLifecycleOwner) {
                mysteryTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            showViewModel.scifiTvshows.observe(viewLifecycleOwner) {
                scifiTvShowAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

        }

        return binding.root
    }

    private fun bindUi() {

        binding.apply {
            imageSlider.apply {
                setIndicatorAnimation(IndicatorAnimationType.WORM)
                setSliderAdapter(slideAdapter)
                startAutoCycle()
            }

            rvPopulartvshow.apply {
                setHasFixedSize(true)
                adapter = popularTvShowAdapter
            }

            morePopular.setOnClickListener {
                comunicator = context as Communicator
                comunicator.passKey("PopularShow")
            }

            rvComedyshow.apply {
                setHasFixedSize(true)
                adapter = comedyTvShowAdapter
            }

                moreComedy.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("ComedyShow")
                }

            rvAnimationshow.apply {
                setHasFixedSize(true)
                adapter = animationTvShowAdapter
            }

                moreAnimation.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("AnimationShow")
                }

            rvReality.apply {
                setHasFixedSize(true)
                adapter = realityTvShowAdapter
            }

                moreReaity.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("RealityShow")
                }

            rvToprated.apply {
                setHasFixedSize(true)
                adapter = topratedTvShowAdapter
            }

                moreTopRated.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("TopShow")
                }

            rvActionAdventure.apply {
                setHasFixedSize(true)
                adapter = actionadventureTvShowAdapter
            }

                moreAction.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("ActionShow")
                }

            rvCrimeshow.apply {
                setHasFixedSize(true)
                adapter = crimeTvShowAdapter
            }

                moreCrime.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("CrimeShow")
                }

            rvDocumentaryshows.apply {
                setHasFixedSize(true)
                adapter = documentryTvShowAdapter
            }

                moreDocumentry.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("DocumentryShow")
                }

            rvNews.apply {
                setHasFixedSize(true)
                adapter = newsTvShowAdapter
            }

                moreNews.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("NewsShow")
                }

            rvDramashows.apply {
                setHasFixedSize(true)
                adapter = dramaTvShowAdapter
            }

                moreDrama.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("DramaShow")
                }

            rvFamilyshows.apply {
                setHasFixedSize(true)
                adapter = familyTvShowAdapter
            }

                moreFamily.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("FamilyShow")
                }

            rvKidsshow.apply {
                setHasFixedSize(true)
                adapter = kidsTvShowAdapter
            }

                moreKids.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("KidsShow")
                }

            rvMysteryshows.apply {
                setHasFixedSize(true)
                adapter = mysteryTvShowAdapter
            }

                moreMystery.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("MysteryShow")
                }

            rvScififantasy.apply {
                setHasFixedSize(true)
                adapter = scifiTvShowAdapter
            }

                moreScifi.setOnClickListener {
                    comunicator = context as Communicator
                    comunicator.passKey("ScifiShow")
                }

            popularTvShowAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    showlayout.isVisible = loadState.source.refresh is LoadState.NotLoading
                    errorText.isVisible = loadState.source.refresh is LoadState.Error
                    retryButton.isVisible = loadState.source.refresh is LoadState.Error

                    //empty view
                    if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && popularTvShowAdapter.itemCount < 1) {
                        showlayout.isVisible = false
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
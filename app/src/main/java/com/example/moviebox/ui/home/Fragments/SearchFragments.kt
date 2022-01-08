package com.example.moviebox.ui.home.Fragments

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebox.Adapter.GenreAdapter
import com.example.moviebox.Adapter.SearchAdapter
import com.example.moviebox.Adapter.ViewAllAdapter
import com.example.moviebox.ModelClass.movieGenres
import com.example.moviebox.ModelClass.tvShowGenres
import com.example.moviebox.R
import com.example.moviebox.Repository.MoviesRepository
import com.example.moviebox.Repository.ShowsRepository
import com.example.moviebox.databinding.FragmentSearchBinding
import com.example.moviebox.ui.home.MovieViewModel.MovieViewModelFactory
import com.example.moviebox.ui.home.MovieViewModel.MoviesViewModel
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModel
import com.example.moviebox.ui.home.ShowViewModel.ShowViewModelFactory
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragments : Fragment(R.layout.fragment_search), GenreAdapter.OnGenreClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel
    private lateinit var showViewModel : ShowViewModel
    private lateinit var secondAdapter: SearchAdapter

    private lateinit var viewAllAdapter: ViewAllAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        secondAdapter = SearchAdapter(requireContext(), true)

        viewAllAdapter = ViewAllAdapter(requireContext(), true)

        val moviesRepository = MoviesRepository()
        val viewModelFactory = MovieViewModelFactory(moviesRepository)
        viewModel =ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        val showRepository = ShowsRepository()
        val showViewMOdelFactory = ShowViewModelFactory(showRepository)
        showViewModel =ViewModelProvider(this, showViewMOdelFactory)[ShowViewModel::class.java]

        bindUI()

        binding.apply {
            editText.setOnEditorActionListener { _, actionId, _ ->
                emptyState.isVisible = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.apply {
                        viewModel.search(editText.text.toString().trim())
                            .observe(viewLifecycleOwner) {
                                secondAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                                binding.recyclerView.scheduleLayoutAnimation()
                                editText.clearFocus()
                            }
                    }
                }
                true
            }
        }

        return binding.root
    }


    private fun bindUI() {

        binding.apply {

            moviesGenreRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                adapter = GenreAdapter(
                    context, movieGenres,
                    this@SearchFragments,
                    context.getString(R.string.movie),
                )
                addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        val position = parent.getChildAdapterPosition(view)
                        outRect.left =
                            if (position % 2 == 0) 0 else 10
                        outRect.right =
                            if (position % 2 == 0) 10 else 0
                        outRect.bottom = 20
                        if (position == 0 || position == 1) {
                            outRect.top = 20
                        }
                    }
                })
            }

            tvShowsGenreRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        val position = parent.getChildAdapterPosition(view)
                        outRect.left =
                            if (position % 2 == 0) 0 else 10
                        outRect.right =
                            if (position % 2 == 0) 10 else 0
                        outRect.bottom = 20
                        if (position == 0 || position == 1) {
                            outRect.top = 20
                        }
                    }
                })
                adapter = GenreAdapter(
                    context,
                    tvShowGenres,
                    this@SearchFragments,
                    context.getString(R.string.tv_show),
                )
            }

            secondAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    if (binding.editText.text.isNotEmpty()) {
                        recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                    }
                    errorText.isVisible = loadState.source.refresh is LoadState.Error

                    //empty view
                    if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && secondAdapter.itemCount < 1) {
                        recyclerView.isVisible = false
                        emptyState.isVisible = true
                    }
                }
            }
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = secondAdapter

            secondAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    if (binding.editText.text.isNotEmpty()) {
                        recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                    }
                    errorText.isVisible = loadState.source.refresh is LoadState.Error

                    //empty view
                    if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && secondAdapter.itemCount < 1) {
                        recyclerView.isVisible = false
                        emptyState.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onGenreClick(position: Int, type: String) {
//        binding.emptyState.visibility = View.GONE
//        binding.recyclerView.visibility = View.VISIBLE
//        if(type == context?.getString(R.string.tv_show)) {
//            showViewModel.getGenreTVShows(tvShowGenres[position].id).observe(viewLifecycleOwner, {
//                secondMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
//                binding.recyclerView.scheduleLayoutAnimation()
//            })
//        } else {
//            viewModel   .getGenreMovies(movieGenres[position].id).observe(viewLifecycleOwner, {
//                secondMovieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
//                binding.recyclerView.scheduleLayoutAnimation()
//            })
//        }
    }

}
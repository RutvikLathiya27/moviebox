package com.example.moviebox.ui.home.ShowViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.ModelClass.ShowDetailModel
import com.example.moviebox.ModelClass.Video
import com.example.moviebox.Repository.ShowsRepository
import kotlinx.coroutines.launch

class ShowViewModel(private val showsRepository: ShowsRepository) : ViewModel() {

    private var readylatestTvshows: MutableLiveData<List<MovieModel>> = MutableLiveData()
    private var tvShowDetails: MutableLiveData<ShowDetailModel> = MutableLiveData()
    private var tvShowVideos: MutableLiveData<List<Video>> = MutableLiveData()


    fun readyLatestTvshows() {
        viewModelScope.launch {
            showsRepository.getLatestTvshow()
            readylatestTvshows.value = showsRepository.latestTvshow
        }
    }

    fun readyTvShowDetails(showId: String?) {
        viewModelScope.launch {
            showsRepository.getShowDetails(showId)
            tvShowDetails.value = showsRepository.showDetail
        }
    }

    fun readyTvShowVideos(tvShowId: String) {
        viewModelScope.launch {
            showsRepository.getTvShowVideos(tvShowId)
            tvShowVideos.value = showsRepository.tvShowVideos
        }
    }

    val popularTvshows = showsRepository.getPopulatTvshow().cachedIn(viewModelScope)
    val comedyTvshows = showsRepository.getComedtTvshow().cachedIn(viewModelScope)
    val animationTvshows = showsRepository.getAnimationTvshows().cachedIn(viewModelScope)
    val realityTvshows = showsRepository.getRealityTvshow().cachedIn(viewModelScope)
    val topratedTvshows = showsRepository.getTopRatedTvshows().cachedIn(viewModelScope)
    val actionadventureTvshows = showsRepository.getActionAdventureTvshows().cachedIn(viewModelScope)
    val crimeTvshows = showsRepository.getCrimeTvshows().cachedIn(viewModelScope)
    val documentryTvshows = showsRepository.getDocumentryTvshows().cachedIn(viewModelScope)
    val newsTvshows = showsRepository.getNewsTvshows().cachedIn(viewModelScope)
    val dramaTvshows = showsRepository.getDramaTvshows().cachedIn(viewModelScope)
    val familyTvshows = showsRepository.getFamilyTvshows().cachedIn(viewModelScope)
    val kidsTvshows = showsRepository.getKidsTvshows().cachedIn(viewModelScope)
    val mysteryTvshows = showsRepository.getMysteryTvshows().cachedIn(viewModelScope)
    val scifiTvshows = showsRepository.getScifiTvshows().cachedIn(viewModelScope)

    fun similarTvShow(tvShowId: String?)= showsRepository.getSimilarTvshows(tvShowId).cachedIn(viewModelScope)

    fun getLatestTvshows(): LiveData<List<MovieModel>> = readylatestTvshows
    fun getShowDetails(): LiveData<ShowDetailModel> = tvShowDetails

    fun getTvShowVideos(): LiveData<List<Video>> = tvShowVideos


}
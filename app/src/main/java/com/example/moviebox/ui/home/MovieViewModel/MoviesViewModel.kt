package com.example.moviebox.ui.home.MovieViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviebox.ModelClass.MovieDetailModel
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.ModelClass.Video
import com.example.moviebox.ModelClass.VideoResponse
import com.example.moviebox.Repository.MoviesRepository
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MoviesRepository): ViewModel() {

    private var movieDetails: MutableLiveData<MovieDetailModel> = MutableLiveData()
    private var nowPlayingMovies: MutableLiveData<List<MovieModel>> = MutableLiveData()
    private var movieVideo : MutableLiveData<List<Video>> = MutableLiveData()


    fun readyMovieDetails(movieId: String?) {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId)
            movieDetails.value = movieRepository.movieDetails
        }
    }

    fun readyNowPlayingMovies() {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies()
            nowPlayingMovies.value = movieRepository.nowPlayingMovies
        }
    }

    fun readyMovieVideo(movieID: String){
        viewModelScope.launch {
            movieRepository.getMovieVideo(movieID)
            movieVideo.value = movieRepository.movieVideo
        }
    }


    fun getMovieVideo() : LiveData<List<Video>> = movieVideo

    fun similarmovie(movieId: String?): LiveData<PagingData<MovieModel>> = movieRepository.getSimilarMovie(movieId).cachedIn(viewModelScope)


    val trendingMovies = movieRepository.getTrendingMovie().cachedIn(viewModelScope)
    val actionadventureMovies = movieRepository.getActionAdventureMovie().cachedIn(viewModelScope)
    val animationMovies = movieRepository.getAnimationMovie().cachedIn(viewModelScope)
    val dramaMovies = movieRepository.getDramaMovie().cachedIn(viewModelScope)
    val popularMovies = movieRepository.getPupularMovie().cachedIn(viewModelScope)
    val comedyMovies = movieRepository.getComedyMovie().cachedIn(viewModelScope)
    val crimeMovies = movieRepository.getCrimeMovie().cachedIn(viewModelScope)
    val topratedMovies = movieRepository.getTopRatedMovie().cachedIn(viewModelScope)
    val familyMovies = movieRepository.getFamilyMovie().cachedIn(viewModelScope)
    val documentryMovies = movieRepository.getDocumentryMovie().cachedIn(viewModelScope)
    val fantacyMovies = movieRepository.getFantacyMovie().cachedIn(viewModelScope)
    val horrorMovies = movieRepository.getHorrorMovie().cachedIn(viewModelScope)
    val romanceMovies = movieRepository.getRomanceMovie().cachedIn(viewModelScope)
    val scifiMovies = movieRepository.getScfiMovie().cachedIn(viewModelScope)
    val warMovies = movieRepository.getWarMovie().cachedIn(viewModelScope)

    fun search(name : String)  = movieRepository.getSearch(name).cachedIn(viewModelScope)

    fun getMovieDetails(): LiveData<MovieDetailModel> = movieDetails
    fun getNowPlayingMovies(): LiveData<List<MovieModel>> = nowPlayingMovies


}
package com.example.moviebox.Repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.moviebox.ModelClass.MovieDetailModel
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.ModelClass.Video
import com.example.moviebox.ModelClass.VideoResponse
import com.example.moviebox.Network.RetrofitBuilder
import com.example.moviebox.Network.Url
import com.example.moviebox.Pagination.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MoviesRepository() {

    var api = RetrofitBuilder.api

    var movieDetails: MovieDetailModel? = null
    var nowPlayingMovies: List<MovieModel>? = null
    var movieVideo: List<Video>? = null

    suspend fun getNowPlayingMovies() = withContext(Dispatchers.IO) {
        try {
            val response = api.getNowPlayingMovie(Url.API_KEy)
                nowPlayingMovies = response.results

        } catch (e: IOException) {
            Log.d("main", "getMovie: ${e.message}")
        } catch (e: HttpException) {
            Log.d("main", "getMovie: ${e.message}")
        }
    }

    suspend fun getMovieDetails(movieId: String?) = withContext(Dispatchers.IO) {
        try {
            val response = api.getMovieDetail(movieId.toString(),Url.API_KEy)
                movieDetails = response.body()!!

        } catch (e: IOException) {
            Log.d("main", "getMovie: ${e.message}")
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) {
                Log.d("main", "getMovie: ${e.message}")
            }
        }
    }

    suspend fun getMovieVideo(movieId: String?) = withContext(Dispatchers.IO) {
        try {
            val response = api.getMovieVideo(movieId.toString(),Url.API_KEy)
                movieVideo = response.body()!!.results

        } catch (e: IOException) {
            Log.d("main", "getMovie: ${e.message}")
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) {
                Log.d("main", "getMovie: ${e.message}")
            }
        }
    }

    fun getTrendingMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TrendingMoviePaggingSource(api) }
        ).liveData

    fun getActionAdventureMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ActionMoviePaggingSource(api) }
        ).liveData

    fun getAnimationMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimationMoviePaggingSource(api) }
        ).liveData


    fun getDramaMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DramaMoviePaggingSource(api) }
        ).liveData

    fun getPupularMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviePaggingSource(api) }
        ).liveData

    fun getComedyMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ComedyMoviePaggingSource(api) }
        ).liveData

    fun getCrimeMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CrimeMoviePaggingSource(api) }
        ).liveData

    fun getTopRatedMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedMoviePaggingSource(api) }
        ).liveData

    fun getFamilyMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FamilyMoviePaggingSource(api) }
        ).liveData

    fun getDocumentryMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DocumentaryMoviePaggingSource(api) }
        ).liveData

    fun getFantacyMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FantacyMoviePaggingSource(api) }
        ).liveData

    fun getHorrorMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { HorrorMoviePaggingSource(api) }
        ).liveData

    fun getRomanceMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RomanceMoviePaggingSource(api) }
        ).liveData

    fun getScfiMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SciFiMoviePaggingSource(api) }
        ).liveData

    fun getWarMovie() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { WarMoviePaggingSource(api) }
        ).liveData

    fun getSimilarMovie(movieId: String?) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SimilarMoviePagingSource(movieId,api) }
        ).liveData

    fun getSearch(name: String?) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(name,api) }
        ).liveData

}
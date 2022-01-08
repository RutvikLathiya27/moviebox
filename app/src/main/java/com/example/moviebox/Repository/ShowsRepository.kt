package com.example.moviebox.Repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.ModelClass.ShowDetailModel
import com.example.moviebox.ModelClass.Video
import com.example.moviebox.Network.RetrofitBuilder
import com.example.moviebox.Network.Url
import com.example.moviebox.Pagination.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ShowsRepository() {

    var api = RetrofitBuilder.api

    var latestTvshow : List<MovieModel>? = null
    var showDetail : ShowDetailModel? = null
    var tvShowVideos : List<Video>? = null

    suspend fun getLatestTvshow() = withContext(Dispatchers.IO) {
        try {
            val response = api.getPopuarTVShow(Url.API_KEy)
            latestTvshow = response.results

        } catch (e: IOException) {
            Log.d("main", "getMovie: ${e.message}")
        } catch (e: HttpException) {
            Log.d("main", "getMovie: ${e.message}")
        }
    }

    suspend fun getTvShowVideos(tvShowId: String) = withContext(Dispatchers.IO) {
        try {
            val response = api.getTvShowVideos(tvShowId, Url.API_KEy)
                tvShowVideos = response.body()!!.results
        }catch (e: IOException) {
            Log.d("main", "getMovie: ${e.message}")
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) {
                Log.d("main", "getMovie: ${e.message}")
            }
        }
    }

    suspend fun getShowDetails(showId: String?) = withContext(Dispatchers.IO) {
        try {
            val response = api.getShowDetail(showId.toString(),Url.API_KEy)
            showDetail = response.body()!!

        } catch (e: IOException) {
            Log.d("main", "getMovie: ${e.message}")
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) {
                Log.d("main", "getMovie: ${e.message}")
            }
        }
    }

    fun getPopulatTvshow() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularTvshowPaggingSource(api) }
        ).liveData

    fun getComedtTvshow() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ComedyTvshowPaggingSource(api) }
        ).liveData

    fun getAnimationTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimationTvshowPaggingSource(api) }
        ).liveData

    fun getRealityTvshow() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RealityTvshowPaggingSource(api) }
        ).liveData

    fun getTopRatedTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedTvshowPaggingSource(api) }
        ).liveData

    fun getActionAdventureTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ActionAdventureTvshowPaggingSource(api) }
        ).liveData

    fun getCrimeTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CrimeTvshowPaggingSource(api) }
        ).liveData

    fun getDocumentryTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DocumentryTvshowPaggingSource(api) }
        ).liveData

    fun getNewsTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsTvshowPaggingSource(api) }
        ).liveData

    fun getDramaTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DramaTvshowPaggingSource(api) }
        ).liveData

    fun getFamilyTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FamilyTvshowPaggingSource(api) }
        ).liveData

    fun getKidsTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { KidsTvshowPaggingSource(api) }
        ).liveData

    fun getMysteryTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MystreyTvshowPaggingSource(api) }
        ).liveData

    fun getScifiTvshows() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ScifiTvshowPaggingSource(api) }
        ).liveData

    fun getSimilarTvshows(showId: String?) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SimilarShowsPagingSource(showId,api) }
        ).liveData

}
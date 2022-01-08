package com.example.moviebox.Pagination

import android.net.Uri
import androidx.paging.PagingSource
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.Network.Api
import com.example.moviebox.Network.Url
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class TrendingMoviePaggingSource(
private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getLatestTrendingAll(Url.API_KEy, position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}

class TopRatedMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getTopRatedMovie(Url.API_KEy, position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class PopularMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getPopularMovie(Url.API_KEy, position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class ActionMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getActionAdventureMovie(Url.API_KEy, "28", "12", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class ComedyMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getComedyMovie(Url.API_KEy, "35", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class DramaMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getDramaMovie(Url.API_KEy,"18", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class FamilyMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getFamilyMovie(Url.API_KEy, "10751", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class CrimeMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getCrimeMovie(Url.API_KEy,"80", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class DocumentaryMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getDocumentaryMovie(Url.API_KEy, "99", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class FantacyMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getFantacyMovie(Url.API_KEy, "14", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class HorrorMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getHorrorMovie(Url.API_KEy, "27", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class SciFiMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getSciFiMovie(Url.API_KEy, "878",position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class  WarMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getWarMovie(Url.API_KEy, "10752", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class  AnimationMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getAnimationMovie(Url.API_KEy, "16", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class  RomanceMoviePaggingSource(
    private val api: Api
):PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getRomanceMovie(Url.API_KEy, "10749", position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class SimilarMoviePagingSource(
    private val movieId: String?,
    private val movieApi: Api
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = movieApi.getSimilarMovie(movieId, Url.API_KEy, position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class SearchPagingSource(
    private val name: String?,
    private val movieApi: Api
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = movieApi.getSearch(Url.API_KEy, name, position)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
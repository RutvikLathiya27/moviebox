package com.example.moviebox.Pagination

import androidx.paging.PagingSource
import com.example.moviebox.ModelClass.MovieModel
import com.example.moviebox.Network.Api
import com.example.moviebox.Network.Url
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class PopularTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getPopuarTVShow(Url.API_KEy)
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

class ComedyTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getComedyShows(Url.API_KEy,"35", position)
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

class AnimationTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getAnimationShows(Url.API_KEy,"16", position)
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

class RealityTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getRealityShows(Url.API_KEy,"10764", position)
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

class TopRatedTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getRatedTVShow(Url.API_KEy, position)
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

class ActionAdventureTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getActionAdventureShows(Url.API_KEy,"10759", position)
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

class CrimeTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getCrimeShows(Url.API_KEy,"80", position)
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

class DocumentryTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getDocumentryShows(Url.API_KEy,"99", position)
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

class NewsTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getNewsShows(Url.API_KEy,"10763", position)
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

class DramaTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getDramaShows(Url.API_KEy,"18",position)
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

class FamilyTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getFamilyShows(Url.API_KEy,"10751", position)
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

class KidsTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getKidsShows(Url.API_KEy,"10762", position)
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

class MystreyTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getMysteryShows(Url.API_KEy,"9648", position)
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

class ScifiTvshowPaggingSource(
    private val api: Api
): PagingSource<Int, MovieModel>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = api.getScifiFantacyShows(Url.API_KEy,"10765", position)
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

class SimilarShowsPagingSource(
    private val showid: String?,
    private val movieApi: Api
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = movieApi.getSimilarShoww(showid, Url.API_KEy, position)
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


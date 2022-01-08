package com.example.moviebox.Network

import com.example.moviebox.ModelClass.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    //Movie

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("api_key") api : String) : Movie

    @GET("trending/movie/week")
    suspend fun getLatestTrendingAll(@Query("api_key") api: String, @Query("page") page: Int) : Movie

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") api:String, @Query("page") page: Int) : Movie

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") api: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getComedyMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getDramaMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getFamilyMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getCrimeMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getDocumentaryMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getFantacyMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getHorrorMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getRomanceMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getSciFiMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getWarMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getAnimationMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/movie")
    suspend fun getActionAdventureMovie(@Query("api_key") api: String, @Query("with_genres") genreId: String , @Query("with_genres") genreId2: String, @Query("page") page: Int) : Movie

    //ShowApi

    @GET("tv/top_rated")
    suspend fun getRatedTVShow(@Query("api_key") api: String, @Query("page") page: Int ) : Movie

    @GET("tv/popular")
    suspend fun getPopuarTVShow(@Query("api_key") api : String) : Movie

    @GET("tv/latest")
    suspend fun getLatestTVShow(@Query("api_key") api : String, @Query("page") page: Int ) : Movie

    @GET("discover/tv")
    suspend fun getActionAdventureShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getAnimationShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getComedyShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getRealityShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getCrimeShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getDocumentryShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getNewsShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getDramaShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getFamilyShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getKidsShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getMysteryShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    @GET("discover/tv")
    suspend fun getScifiFantacyShows(@Query("api_key") api: String, @Query("with_genres") genreId: String, @Query("page") page: Int) : Movie

    //Search Query
    @GET("search/multi")
    suspend fun getSearch(@Query("api_key") api: String, @Query("query") name: String?, @Query("page") page: Int) : Movie

    //movie Detail
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: String, @Query("api_key") api: String?) : Response<MovieDetailModel>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(@Path("movie_id") id: String?, @Query("api_key") api: String, @Query("page") page: Int) :Movie

    //show Detail
    @GET("tv/{tvShow_id}")
    suspend fun getShowDetail(@Path("tvShow_id") id: String?, @Query("api_key") api: String?) : Response<ShowDetailModel>

    @GET("tv/{tvShow_id}/similar")
    suspend fun getSimilarShoww(@Path("tvShow_id") id: String?, @Query("api_key") api: String, @Query("page") page: Int) :Movie

    @GET("discover/tv")
    suspend fun getGenreTVShows(@Query("api_key") id : String,@Query("with_genres") genreId: Int, @Query("page") page: Int) : Genre

    //trailer
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(@Path("movie_id") id : String, @Query("api_key") api: String) : Response<VideoResponse>

    @GET("tv/{tvShow_id}/videos")
    suspend fun getTvShowVideos(@Path("tvShow_id") id: String, @Query("api_key") api: String): Response<VideoResponse>

}
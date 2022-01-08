package com.example.moviebox.ModelClass

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language

data class Movie(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<MovieModel>
)

data class MovieModel(
    @SerializedName("title", alternate = ["name"])
    val movieTitle: String,
    @SerializedName("poster_path")
    val moviePoster: String,
    @SerializedName("backdrop_path")
    val backPoster: String,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val releaseDate: String,
    @SerializedName("vote_average")
    val averageRate: Number,
    @SerializedName("id")
    val movieId: String
)
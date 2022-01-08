package com.example.moviebox.ModelClass

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language

data class MovieDetailModel(

    val id: String,
    @SerializedName("backdrop_path")
    val backgroundPoster: String,
    @SerializedName("poster_path")
    val moviePoster: String,
    @SerializedName("original_title")
    val movieName: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: String,
    @SerializedName("tagline")
    val tagline : String,
    @SerializedName("overview")
    val movieDescription : String,
    @SerializedName("vote_average")
    val voteAverage: String,
    @SerializedName("budget")
    val budget : Int,
    @SerializedName("revenue")
    val revenue : Int,
    @SerializedName("original_language")
    val language: String

)

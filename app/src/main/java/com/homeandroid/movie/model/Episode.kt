package com.homeandroid.movie.model


import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("Episode")
    val episode: String?,
    @SerializedName("imdbID")
    val imdbID: String?,
    @SerializedName("imdbRating")
    val imdbRating: String?,
    @SerializedName("Released")
    val released: String?,
    @SerializedName("Title")
    val title: String?
)
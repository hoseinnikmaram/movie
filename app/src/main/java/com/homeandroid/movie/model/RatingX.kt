package com.homeandroid.movie.model


import com.google.gson.annotations.SerializedName

data class RatingX(
    @SerializedName("Source")
    val source: String?,
    @SerializedName("Value")
    val value: String?
)
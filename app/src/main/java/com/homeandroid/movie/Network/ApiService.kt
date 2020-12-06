package com.homeandroid.movie.Network

import com.homeandroid.movie.model.Details_movie_Model
import com.homeandroid.movie.model.Search_Model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET
    fun get_movies(
    @Query("t") type: String,
    @Query("Season") Season: String,
    @Query("apikey") key: String
    ): Single<Search_Model>

    @GET("?apikey=d9b5c0f8")
    fun get_movie_by_title(
        @Query("s") text: String
    ): Single<Search_Model>

    @GET("?apikey=d9b5c0f8")
    fun get_movie_by_Details(
        @Query("i") type: String): Single<Details_movie_Model>
}
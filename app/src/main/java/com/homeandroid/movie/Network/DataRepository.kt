package com.homeandroid.movie.Network

import android.util.Log
import com.homeandroid.movie.model.Details_movie_Model
import com.homeandroid.movie.model.Search_Model
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


    public fun  getMovies(apiService: ApiService,type:String,apikey:String,season:String): Single<Search_Model>? {
        return apiService.get_movies(type,season,apikey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable:Throwable -> Log.e("error", throwable.message.toString()) }

    }

    fun SearchMovies(apiService: ApiService,text:String):Single<Search_Model> {
        return apiService.get_movie_by_title(text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable:Throwable -> Log.e("error", throwable.message.toString()) }

    }

    fun DetailsMovies(apiService: ApiService,id:String):Single<Details_movie_Model> {
        return apiService.get_movie_by_Details(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable:Throwable -> Log.e("error", throwable.message.toString()) }

    }


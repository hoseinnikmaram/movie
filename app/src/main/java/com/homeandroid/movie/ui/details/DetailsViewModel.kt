package com.homeandroid.movie.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homeandroid.movie.Network.ApiService
import com.homeandroid.movie.Network.DetailsMovies
import com.homeandroid.movie.model.Details_movie_Model
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class DetailsViewModel(private val apiService: ApiService, private val compositeDisposable: CompositeDisposable) : ViewModel() {

    private var _details_movie= MutableLiveData<Details_movie_Model>()

     var set_visible= MutableLiveData(true)

    val details_movie: LiveData<Details_movie_Model>
        get() = _details_movie
    fun get_movie_details(id:String) {

        compositeDisposable.add(
                DetailsMovies(apiService, id)
                        .subscribeWith(object : DisposableSingleObserver<Details_movie_Model>() {
                            override fun onSuccess(response: Details_movie_Model) {
                                set_visible.value=false
                                _details_movie.value=response
                                Log.d("TEST", "onSuccess getFriendList")
                            }

                            override fun onError(e: Throwable) {
                                Log.e("TEST", e.toString())
                            }
                        })!!
        )


    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}
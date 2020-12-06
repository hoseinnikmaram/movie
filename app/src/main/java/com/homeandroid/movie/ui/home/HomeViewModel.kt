package com.homeandroid.movie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homeandroid.movie.Network.ApiService
import com.homeandroid.movie.Network.SearchMovies
import com.homeandroid.movie.model.Search_Model
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class HomeViewModel(private val apiService: ApiService,private val compositeDisposable:CompositeDisposable) : ViewModel() {

     var _list_movie= MutableLiveData<Search_Model>()


    val list_movie: LiveData<Search_Model>
        get() = _list_movie
     fun get_movie(text:String) {

            compositeDisposable.add(
                SearchMovies(apiService, text)
                    .subscribeWith(object : DisposableSingleObserver<Search_Model>() {
                        override fun onSuccess(response: Search_Model) {
                            _list_movie.value=response
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
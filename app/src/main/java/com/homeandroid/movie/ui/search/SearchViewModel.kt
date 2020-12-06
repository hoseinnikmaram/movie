package com.homeandroid.movie.ui.search

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homeandroid.movie.MovieApplication
import com.homeandroid.movie.Network.ApiService
import com.homeandroid.movie.Network.SearchMovies
import com.homeandroid.movie.model.Search_Model
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(private val apiService: ApiService, private val compositeDisposable: CompositeDisposable,private val context: Context) : ViewModel() {

    private var _list_movie= MutableLiveData<Search_Model>()
    var isgetmove= MutableLiveData<Boolean>(false)


    val list_movie: LiveData<Search_Model>
        get() = _list_movie
    fun get_movie(text:String) {

        compositeDisposable.add(
                SearchMovies(apiService, text)
                        .subscribeWith(object : DisposableSingleObserver<Search_Model>() {
                            override fun onSuccess(response: Search_Model) {
                                if (response.Response.equals("True"))
                                { isgetmove.value=true
                                _list_movie.value=response}
                               else
                                    Toast.makeText(context, "جستجو بی نتیجه است", Toast.LENGTH_LONG).show()

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
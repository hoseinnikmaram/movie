package com.homeandroid.movie.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import com.homeandroid.movie.Network.ApiService
import com.homeandroid.movie.ui.home.HomeFragment
import com.homeandroid.movie.ui.search.SearchFragment
import com.homeandroid.movie.util.ViewPagerFragmentAdapter
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val base_url="http://www.omdbapi.com/"
fun provideRetrofit(okHttpclient:OkHttpClient):ApiService{

    return Retrofit.Builder()
        .baseUrl(base_url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpclient)
        .build()
        .create(ApiService::class.java)

}

fun provideOkHttpClient(loggingInterceptor:LoggingInterceptor):OkHttpClient{

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

}

fun provideLoggingInterceptor(): LoggingInterceptor {

    return LoggingInterceptor.Builder()
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .addHeader("version","1")
        .build()
}

fun provideCompositeDisposable(): CompositeDisposable {

    return CompositeDisposable()

}

fun provideFragmentActivity(): FragmentActivity {

    return FragmentActivity()
}

fun provideListFragment(): List<Fragment> {

     return mutableListOf(HomeFragment(),SearchFragment())
}

fun provideViewPagerFragmentAdapter(listFragment:  List<Fragment>,fragmentActivity: FragmentActivity): ViewPagerFragmentAdapter{
        return ViewPagerFragmentAdapter(listFragment,fragmentActivity)
}
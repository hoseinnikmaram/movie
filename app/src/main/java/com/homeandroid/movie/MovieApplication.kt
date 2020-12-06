package com.homeandroid.movie

import android.app.Application
import com.homeandroid.movie.di.applicationModule
import com.homeandroid.movie.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication: Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MovieApplication)
            modules(applicationModule,viewModelsModule)

        }    }



}
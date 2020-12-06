package com.homeandroid.movie.di

import com.homeandroid.movie.ui.details.DetailsViewModel
import com.homeandroid.movie.ui.home.HomeViewModel
import com.homeandroid.movie.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { provideCompositeDisposable() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
  //  single { provideFragmentActivity() }
   // single { provideListFragment() }
   // single { provideViewPagerFragmentAdapter(get(),get()) }

}


val viewModelsModule = module {
   viewModel { HomeViewModel(get(),get()) }
    viewModel { DetailsViewModel(get(),get()) }
    viewModel { SearchViewModel(get(),get(),get()) }

}
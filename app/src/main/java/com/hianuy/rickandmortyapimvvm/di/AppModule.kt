package com.hianuy.rickandmortyapimvvm.di

import com.hianuy.rickandmortyapimvvm.BuildConfig.*
import com.hianuy.rickandmortyapimvvm.data.api.RetrofitServiceAPI
import com.hianuy.rickandmortyapimvvm.repository.CharacterRepository
import com.hianuy.rickandmortyapimvvm.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single<Retrofit> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }


    single<RetrofitServiceAPI> {
        get<Retrofit>().create(RetrofitServiceAPI::class.java)
    }

    single<CharacterRepository> {
        CharacterRepository(get())
    }

    viewModel<MainViewModel>{
        MainViewModel(get())
    }

}
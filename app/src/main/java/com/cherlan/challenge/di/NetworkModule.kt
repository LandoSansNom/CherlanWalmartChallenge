package com.cherlan.challenge.di

import com.cherlan.challenge.data.remote.ApiCall
import com.cherlan.challenge.data.remote.ApiService
import com.cherlan.challenge.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://gist.githubusercontent.com/peymano-wmt/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { ApiCall(get()) }
}
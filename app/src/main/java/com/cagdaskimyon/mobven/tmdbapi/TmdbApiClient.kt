package com.cagdaskimyon.mobven.tmdbapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient


const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_URL = "http://image.tmdb.org/t/p/w500"
const val API_KEY = "ae57e996a81ca1586a05a1d4211b1818"


object TmdbApiClient {
    val getClient: TmdbApiInterface
        get() {
            val client = OkHttpClient.Builder()
                .addInterceptor(QueryInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(TmdbApiInterface::class.java)
        }
}

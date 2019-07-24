package com.cagdaskimyon.mobven.tmdbapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiInterface {

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("page") page: Int
    ): Call<Movie>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("page") page: Int
    ): Call<Movie>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("page") page: Int
    ): Call<Movie>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): Call<MovieDetail>

    @GET("search/movie")
    fun searchMovie(
        @Query("query") movieQuery: String
    ): Call<Movie>
}
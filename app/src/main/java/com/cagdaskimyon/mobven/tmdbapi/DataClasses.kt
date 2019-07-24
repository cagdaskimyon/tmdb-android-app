package com.cagdaskimyon.mobven.tmdbapi

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("total_results")
    var totalResults: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null,

    @SerializedName("results")
    var results: List<Result>? = null
)

data class Result(

    @SerializedName("vote_count")
    var voteCount: Int? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("vote_average")
    var voteAverage: Double? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null
)


data class MovieDetail (

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    var belongsToCollection: Any? = null,

    @SerializedName("budget")
    var budget: Int? = null,

    @SerializedName("genres")
    var genres: List<Genre>? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("imdb_id")
    var imdbId: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("poster_path")
    var posterPath: Any? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("revenue")
    var revenue: Int? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("vote_average")
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    var voteCount: Int? = null
)

class Genre(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null
)
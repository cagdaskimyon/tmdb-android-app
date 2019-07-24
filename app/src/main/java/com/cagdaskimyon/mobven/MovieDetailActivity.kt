package com.cagdaskimyon.mobven

import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cagdaskimyon.mobven.tmdbapi.IMAGE_URL
import com.cagdaskimyon.mobven.tmdbapi.MovieDetail
import com.cagdaskimyon.mobven.tmdbapi.TmdbApiClient
import com.cagdaskimyon.mobven.utils.database
import com.cagdaskimyon.mobven.utils.formatDate
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailActivity : AppCompatActivity() {

    var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieId = intent!!.extras!!.getInt("movie_id")

        getMovieDetail()

        if (isFavorite(movieId!!)) {
            iv_favorite.setColorFilter(Color.parseColor("#FF0000"))
        }
        else {
            iv_favorite.setColorFilter(Color.parseColor("#A3A3A4"))
        }

        iv_favorite.setOnClickListener {
            if (isFavorite(movieId!!)) {
                deleteFavorite(movieId!!)
                iv_favorite.setColorFilter(Color.parseColor("#A3A3A4"))
            }
            else {
                setFavorite(movieId!!)
                iv_favorite.setColorFilter(Color.parseColor("#FF0000"))
            }
        }
    }


    private fun getMovieDetail() {

        val call: Call<MovieDetail> = TmdbApiClient.getClient.getMovieDetail(movieId!!)
        call.enqueue(object : Callback<MovieDetail> {

            override fun onResponse(call: Call<MovieDetail>?, response: Response<MovieDetail>?) {
                val movieDetail = response!!.body()!!

                tv_movie_detail_title.text = movieDetail.title
                tv_movie_detail_date.text = formatDate(movieDetail.releaseDate!!)
                tv_user_score.text = movieDetail.voteAverage.toString()
                tv_movie_detail_overview.text = movieDetail.overview
                Glide.with(applicationContext)
                    .load(IMAGE_URL + movieDetail.posterPath)
                    .into(iv_movie_detail_poster)
                Glide.with(applicationContext)
                    .load(IMAGE_URL + movieDetail.backdropPath)
                    .into(iv_movie_detail_backdrop)
            }

            override fun onFailure(call: Call<MovieDetail>?, t: Throwable?) {
                Snackbar.make(layout_movie_detail, "Cannot load the movie", Snackbar.LENGTH_LONG).show()
            }

        })
    }

    private fun setFavorite(movieId: Int) {
        val contentValues = ContentValues()
        contentValues.put("movie_id", movieId)
        database.writableDatabase.insert("favorite", null, contentValues)
    }

    private fun isFavorite(movieId: Int): Boolean {
        val query = "SELECT 1 FROM favorite WHERE movie_id=$movieId"
        val result = database.readableDatabase.rawQuery(query, null)
        return if (result.count > 0) {
            result.close()
            true
        } else {
            result.close()
            false
        }
    }

    private fun deleteFavorite(movieId: Int) {
        val query = "DELETE FROM favorite WHERE movie_id = $movieId"
        database.writableDatabase.execSQL(query)
    }

}
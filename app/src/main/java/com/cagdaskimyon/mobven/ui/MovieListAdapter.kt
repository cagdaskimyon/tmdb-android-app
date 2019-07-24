package com.cagdaskimyon.mobven.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.content.Intent
import com.cagdaskimyon.mobven.MovieDetailActivity
import com.cagdaskimyon.mobven.R
import com.cagdaskimyon.mobven.tmdbapi.IMAGE_URL
import com.cagdaskimyon.mobven.tmdbapi.Result
import com.cagdaskimyon.mobven.utils.formatDate


class DataAdapter(private var dataList: List<Result>, private val context: Context) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)

        holder.movieId = dataModel.id
        holder.movieTitle.text = dataModel.title
        holder.releaseDate.text = formatDate(dataModel.releaseDate!!)
        holder.voteAverage.text = dataModel.voteAverage.toString()
        Glide.with(context)
            .load(IMAGE_URL + dataModel.posterPath)
            .into(holder.moviePoster)
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        var movieTitle: TextView
        var moviePoster: ImageView
        var releaseDate: TextView
        var voteAverage: TextView
        var movieId: Int? = null

        init {
            movieTitle = listItemView.findViewById(R.id.tv_movie_title)
            moviePoster = listItemView.findViewById(R.id.iv_movie_poster)
            releaseDate = listItemView.findViewById(R.id.tv_release_date)
            voteAverage = listItemView.findViewById(R.id.tv_vote_average)
            listItemView.setOnClickListener {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra("movie_id", movieId)
                context.startActivity(intent)
            }
        }
    }

}


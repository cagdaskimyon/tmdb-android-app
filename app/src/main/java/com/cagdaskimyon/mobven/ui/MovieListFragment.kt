package com.cagdaskimyon.mobven.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import com.cagdaskimyon.mobven.R
import com.cagdaskimyon.mobven.tmdbapi.Movie
import com.cagdaskimyon.mobven.tmdbapi.Result
import com.cagdaskimyon.mobven.tmdbapi.TmdbApiClient
import kotlinx.android.synthetic.main.fragment_movie_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    var movieList = ArrayList<Result>()
    var movieQuery: String? = null
    var currentPage = 0
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        getMovie()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv_movie_list.layoutManager = LinearLayoutManager(context)
        view.rv_movie_list.adapter = DataAdapter(movieList, context!!)
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(view.context, R.drawable.movie_list_item_divider)!!)
        view.rv_movie_list.addItemDecoration(itemDecoration)


        view.rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading && linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 5) {
                    isLoading = true
                    when (arguments?.getInt(ARG_SECTION_NUMBER)) {
                        1, 2, 3 -> getMovie()
                        else -> searchMovie(movieQuery!!)
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun getMovie() {

        val call: Call<Movie> = when (arguments?.getInt(ARG_SECTION_NUMBER)) {
            1 -> TmdbApiClient.getClient.getNowPlaying(currentPage + 1)
            2 -> TmdbApiClient.getClient.getUpcoming(currentPage + 1)
            3 -> TmdbApiClient.getClient.getTopRated( currentPage + 1)
            else -> TmdbApiClient.getClient.getNowPlaying(currentPage + 1)
        }

        call.enqueue(object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                if (currentPage < response!!.body()!!.totalPages!!)
                    currentPage = response!!.body()!!.page!!
                movieList.addAll(response!!.body()!!.results!!.toList())
                rv_movie_list.adapter?.notifyDataSetChanged()
                isLoading = false
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Snackbar.make(view!!, "Cannot load movies", Snackbar.LENGTH_LONG).show()
            }

        })
    }

    fun searchMovie(movieQuery: String) {
        this.movieQuery = movieQuery

        val call: Call<Movie> = TmdbApiClient.getClient.searchMovie(movieQuery)
        call.enqueue(object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                movieList.clear()
                movieList.addAll(response!!.body()!!.results!!.toList())
                rv_movie_list.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Snackbar.make(view!!, "Cannot get search results", Snackbar.LENGTH_LONG).show()
            }

        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): MovieListFragment {
            return MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

}
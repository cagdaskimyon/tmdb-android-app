package com.cagdaskimyon.mobven

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.cagdaskimyon.mobven.ui.MovieListFragment
import com.cagdaskimyon.mobven.ui.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager


//Compound drawable indices
private const val DRAWABLE_LEFT = 0
private const val DRAWABLE_TOP = 1
private const val DRAWABLE_RIGHT = 2
private const val DRAWABLE_BOTTOM = 3

class MainActivity : AppCompatActivity() {

    var movieSearchResults: MovieListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        /** Hides fragment on create */
        movieSearchResults = supportFragmentManager.findFragmentById(R.id.fragment_search) as MovieListFragment
        hideResultList()

        /** Search movie listeners */
        et_search_movie.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_UP) {
                    if (et_search_movie.compoundDrawables[DRAWABLE_RIGHT] != null && event.rawX >= et_search_movie.right - et_search_movie.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                        hideResultList()
                        hideSoftKeyboard()
                        et_search_movie.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_search, 0, 0, 0)
                        et_search_movie.compoundDrawables[DRAWABLE_LEFT].alpha = 127
                        et_search_movie.text.clear()
                        et_search_movie.clearFocus()

                        return true
                    }
                }
                return false
            }
        })

        et_search_movie.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                et_search_movie.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_search, 0, R.drawable.icon_clear, 0)
                et_search_movie.compoundDrawables[DRAWABLE_LEFT].alpha = 255
            }
        }

        et_search_movie.addTextChangedListener(object : TextWatcher {
            lateinit var userQuery: Editable
            val handler = Handler()
            val runnable = Runnable {
                run {
                    if (userQuery.isNotBlank())
                        delayedSearch(userQuery)
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotBlank()) {
                    userQuery = s

                    handler.removeCallbacks(runnable)
                    handler.postDelayed(runnable, 500)
                }
                else {
                    hideResultList()
                }
            }

            fun delayedSearch(s: Editable) {
                movieSearchResults!!.movieList.clear()
                movieSearchResults!!.currentPage = 0
                movieSearchResults!!.searchMovie(s.toString())
                showResultList()
            }
        })
    }

    private fun showResultList() {
        supportFragmentManager
            .beginTransaction()
            .show(movieSearchResults!!)
            .commit()
    }

    private fun hideResultList() {
        supportFragmentManager
            .beginTransaction()
            .hide(movieSearchResults!!)
            .commit()
    }

    private fun hideSoftKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}
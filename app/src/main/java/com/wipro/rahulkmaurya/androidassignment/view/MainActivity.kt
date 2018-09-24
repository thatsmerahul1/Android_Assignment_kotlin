package com.wipro.rahulkmaurya.androidassignment.view

import android.content.res.Resources
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.wipro.rahulkmaurya.androidassignment.R
import com.wipro.rahulkmaurya.androidassignment.adapter.CustomViewAdapter
import com.wipro.rahulkmaurya.androidassignment.model.Facts
import com.wipro.rahulkmaurya.androidassignment.presenter.ActivityPresenter
import com.wipro.rahulkmaurya.androidassignment.services.RetrofitClientInstance.getFactsServicesClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class used to handle main activity view
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */

class MainActivity : AppCompatActivity(), ActivityPresenter.View {
    private var presenter: ActivityPresenter? = null
    private var progressBar: ProgressBar? = null
    private val swipeContainer : SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = ActivityPresenter(this)

        val swipeContainer : SwipeRefreshLayout = findViewById(R.id.swipeContainer)

        // Setup refresh listener which  will triggers load facts
        swipeContainer.setOnRefreshListener({
            loadFacts()
        })

        initProgressBar()
        showProgressBar()
        loadFacts()
    }

    /**
     * This method will initialize progress bar
     * */
    private fun initProgressBar() {
        progressBar = findViewById(R.id.progressBar)
        val params = RelativeLayout.LayoutParams(Resources.getSystem().displayMetrics.widthPixels,250)
        params.addRule(RelativeLayout.CENTER_VERTICAL )
        params.alignWithParent = true
        progressBar?.layoutParams = params
    }

    /**
     * This method will make an api call for list of facts.
     * */
    private fun loadFacts() {
        val call = getFactsServicesClient().listOfFacts()
        // Execute the call asynchronously.
        call.enqueue(object : Callback<Facts> {
            override fun onResponse(call: Call<Facts>, response: Response<Facts>) {
                presenter?.updateFactsData(response.body())
                hideProgressBar()
                swipeContainer?.isRefreshing = false
            }

            override fun onFailure(call: Call<Facts>, t: Throwable) {
                hideProgressBar()
                presenter?.updateFactsData(null)
                swipeContainer?.isRefreshing = false
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
    /**
     * This method get called from presentation layer after performing business logic to update UI.
     * */
    override fun updateFactsView(facts: Facts?) {
        generateDataList(facts)
    }

    /**
     * For showing progress bar
     * */
    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    /**
     * For hiding progress bar
     * */
    override fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }

    /**
     * This method will initialize Recycler view and custom adapter to inflate fact data.
     * */
    private fun generateDataList(facts: Facts?) {
        val recyclerView: RecyclerView = findViewById(R.id.customRecyclerView)
        val dataNotAvailable: TextView = findViewById(R.id.dataNotAvailable)
        if(facts != null) {
            dataNotAvailable.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            val adapter = CustomViewAdapter(facts.rows!!)
            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
            dataNotAvailable.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

}

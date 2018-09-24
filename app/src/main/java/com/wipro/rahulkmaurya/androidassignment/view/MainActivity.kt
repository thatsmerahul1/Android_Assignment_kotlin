package com.wipro.rahulkmaurya.androidassignment.view

import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleSmall)
        presenter = ActivityPresenter(this)

        initProgressBar()
        showProgressBar()
        loadFacts()
    }

    /**
     * This method will initialize progress bar
     * */
    private fun initProgressBar() {
        progressBar?.isIndeterminate = true
        val params = RelativeLayout.LayoutParams(Resources.getSystem().displayMetrics.widthPixels,
                600)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        this.addContentView(progressBar, params)
    }

    private fun loadFacts() {
        val call = getFactsServicesClient().listOfFacts()
        // Execute the call asynchronously.
        call.enqueue(object : Callback<Facts> {
            override fun onResponse(call: Call<Facts>, response: Response<Facts>) {
                presenter?.updateFactsData(response.body())
                hideProgressBar()
            }

            override fun onFailure(call: Call<Facts>, t: Throwable) {
                hideProgressBar()
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun updateFactsView(facts: Facts?) {
        generateDataList(facts)
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(facts: Facts?) {
        val recyclerView: RecyclerView = findViewById(R.id.customRecyclerView)
        val adapter = CustomViewAdapter(facts!!.rows!!)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}

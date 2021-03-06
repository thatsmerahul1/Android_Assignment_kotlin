package com.wipro.rahulkmaurya.androidassignment.view

import android.content.IntentFilter
import android.content.res.Resources
import android.net.ConnectivityManager
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
import com.wipro.rahulkmaurya.androidassignment.utils.NetworkConnectivityListener
import com.wipro.rahulkmaurya.androidassignment.utils.NetworkUtil


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
        val swipeContainer : SwipeRefreshLayout = findViewById(R.id.swipeContainer)
        initProgressBar()
        showProgressBar()
        presenter = ActivityPresenter(this)

        // Setup refresh listener which  will triggers load facts
        swipeContainer.setOnRefreshListener({
            if(NetworkUtil.getConnectivityStatus(applicationContext) == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                swipeContainer.isRefreshing = false
                Toast.makeText(applicationContext, "Please check your internet connection.", Toast.LENGTH_SHORT).show()
            } else {
                presenter?.pullToRefreshCalled()
            }
        })
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
     * This method get called from presentation layer after performing business logic to update UI.
     * */
    override fun updateFactsView(facts: Facts?) {
        val swipeContainer : SwipeRefreshLayout = findViewById(R.id.swipeContainer)
        swipeContainer.isRefreshing = false
        hideProgressBar()
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

        // Controling visibility of recycler view & dataNotAvailable views.
        when (facts != null) {
            true -> {
                dataNotAvailable.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                supportActionBar?.title = facts?.title
                val adapter = CustomViewAdapter(facts?.rows!!)
                val layoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            false -> {
                dataNotAvailable.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
    }

    /**
     * Registering Net
     * */
    private val networkChangeReceiver: NetworkConnectivityListener = NetworkConnectivityListener()

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(networkChangeReceiver)
    }
}

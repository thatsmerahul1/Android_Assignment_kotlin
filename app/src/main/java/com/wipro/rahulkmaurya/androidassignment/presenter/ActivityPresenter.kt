package com.wipro.rahulkmaurya.androidassignment.presenter

import com.wipro.rahulkmaurya.androidassignment.model.Facts

/**
 * Class used will perform as Presenter of the main activity
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
class ActivityPresenter(private val view: View): FactsServiceListener {
    private var facts: Facts? = null

    init {
        this.facts = Facts("", null)
        facts?.getFacts(this)
    }

    /**
     * This method will update local object of factsView.
     * */
    private fun updateFactsData(factsResponse: Facts?) {
        this.facts = factsResponse
        view.updateFactsView(this.facts)
    }

    /**
     * This method will update local object of factsView.
     * */
    fun pullToRefreshCalled() {
        this.facts = Facts("", null)
        facts?.getFacts(this)
    }

    interface View {
        fun updateFactsView(facts: Facts?)
        fun showProgressBar()
        fun hideProgressBar()
    }

    /**
     * This method will get called when fact service listener will return value.
     * */
    override fun onDataReceived(facts: Facts?) {
        updateFactsData(facts)
    }
}

/**
 * This listener will be registered for response of Fact API .
 * */
interface FactsServiceListener {
    fun onDataReceived(facts: Facts?)
}
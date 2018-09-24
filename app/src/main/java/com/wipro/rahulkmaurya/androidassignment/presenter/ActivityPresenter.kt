package com.wipro.rahulkmaurya.androidassignment.presenter

import com.wipro.rahulkmaurya.androidassignment.model.Facts

/**
 * Class used to <insert description here>
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
class ActivityPresenter(private val view: View) {

    private var facts: Facts? = null

    init {
        this.facts = Facts("", null)
    }

    fun updateFactsData(factsResponse: Facts?) {
        this.facts = factsResponse
        view.updateFactsView(this.facts)
    }

    interface View {
        fun updateFactsView(facts: Facts?)
        fun showProgressBar()
        fun hideProgressBar()

    }
}

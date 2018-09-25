package com.wipro.rahulkmaurya.androidassignment.model

import com.wipro.rahulkmaurya.androidassignment.presenter.FactsServiceListener
import com.wipro.rahulkmaurya.androidassignment.services.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class used to consume Facts object coming from server
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
data class Facts(val title : String?, val rows : MutableList<Fact>?) {

    /**
     * This method will make an api call for list of facts.
     * */
    fun getFacts(factsServiceListener: FactsServiceListener) {
        val call = RetrofitClientInstance.getFactsServicesClient().listOfFacts()
        call.enqueue(object : Callback<Facts> {
            override fun onResponse(call: Call<Facts>, response: Response<Facts>) {
                factsServiceListener.onDataReceived(response.body())
            }
            override fun onFailure(call: Call<Facts>, t: Throwable) {
                factsServiceListener.onDataReceived(null)
            }
        })
    }
}
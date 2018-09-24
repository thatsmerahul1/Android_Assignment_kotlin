package com.wipro.rahulkmaurya.androidassignment.services

import com.wipro.rahulkmaurya.androidassignment.model.Facts
import retrofit2.Call
import retrofit2.http.GET

/**
 * Class used for conatining list of all the fact services.
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
interface FactsServices {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun listOfFacts(): Call<Facts>
}
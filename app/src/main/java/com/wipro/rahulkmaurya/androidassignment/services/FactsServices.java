package com.wipro.rahulkmaurya.androidassignment.services;


import com.wipro.rahulkmaurya.androidassignment.model.Facts;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Class used to <call the all facts services and it will contain whole list of facts services>
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */

public interface FactsServices {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Facts> listOfFacts();
}
package com.wipro.rahulkmaurya.androidassignment.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class used to <give retrofit client and all available services in project>
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private var BASE_URL: String = "https://dl.dropboxusercontent.com";

    fun getFactsServicesClient() : FactsServices {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create<FactsServices>(FactsServices::class.java)
    }
}
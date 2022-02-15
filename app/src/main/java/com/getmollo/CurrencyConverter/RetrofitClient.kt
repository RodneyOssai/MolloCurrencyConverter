package com.getmollo.CurrencyConverter

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //GSON is used to parse the response
    private const val BASE_URL = "http://data.fixer.io/api/"
    private val retrofit: Retrofit? =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

    //With the instance of RETROFIT above we can now create our FetchRatesAPI.
    // Retrofit will now bring life into the methods declared in the FetchRatesApi::class.java
    val fetchRatesAPIService: FetchRatesApi =
        retrofit!!.create(FetchRatesApi::class.java)

}
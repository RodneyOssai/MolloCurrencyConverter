package com.getmollo.CurrencyConverter


import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET


interface FetchRatesApi {


    @GET("latest?access_key=d7704455e1d8f52b1c94c5d3a61bf05c")
    fun getLatestRates(): Call<JsonObject>


}



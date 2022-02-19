package com.getmollo.CurrencyConverter

import SpinnerItemRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext


public class HomeViewModel(private val repository: SpinnerItemRepository,application: Application) : AndroidViewModel(application) {
    var fetchratesApi = RetrofitClient.fetchRatesAPIService
    val currencyList: LiveData<List<SpinnerItem?>?>? = repository.currenciesList


    /**
     * Launching a new coroutine using ViewModelScope to insert the data in a non-blocking way(background)
     */
    fun insert(currency: SpinnerItem) = viewModelScope.launch {
        repository.insert(currency)
    }
    fun update(currency: SpinnerItem) = viewModelScope.launch {
        repository.update(currency)
    }


    //This functions returns different currency rates to the EUR
    private fun getLatestRates() {
        viewModelScope.launch {
            val listResult = fetchratesApi.getLatestRates().enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    //Convert Google GSON JsonObject to string
                    val rString: String = response.body().toString()
                    //Convert String back to ORG.JSON JSONObject
                    val rJson = JSONObject(rString)
                    val ratesObject: JSONObject = rJson.getJSONObject("rates")
                    val keys: Iterator<*> = ratesObject.keys()

                    while (keys.hasNext()) {
                        // loop to get the dynamic key
                        // We expect all the currency Tickers to be returned as keys
                        val dynamicKey = keys.next() as String
                        // get the value of the Currencies below using the key
                        val dynamicValue =
                            ratesObject.getJSONObject(dynamicKey).optDouble(dynamicKey)
                        val drawableResId: Int = getApplication<Application>().applicationContext.resources.getIdentifier(dynamicKey, "drawable", getApplication<Application>().applicationContext.packageName)

                        var item: SpinnerItem = SpinnerItem(dynamicKey, drawableResId, dynamicValue)
                        //Add to room Database so it can be retrieved in  using LiveData
                        viewModelScope.launch {
                            repository.insert(item)
                        }
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    //Do nothing
                }
            })
        }

    }
}
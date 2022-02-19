package com.getmollo.CurrencyConverter

import androidx.lifecycle.LiveData
import androidx.room.*

//This class is our Data Access Object, here we define all the database operations we want to make on the Spinner Item Entity(SQLliteDB)
@Dao
interface SpinnerItemDao {
    @Insert
    fun insert(currency: SpinnerItem?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(currency: SpinnerItem?)

    @Delete
    fun delete(currency: SpinnerItem?)

    @Query("DELETE FROM currency_table")
    fun deleteAll()

    @get:Query("SELECT * FROM currency_table")
    val currenciesList: LiveData<List<SpinnerItem?>?>?

    @Query("SELECT * FROM currency_table WHERE currencyTicker = :ticker")
    fun getSelectedCurrency(ticker: String):SpinnerItem
}
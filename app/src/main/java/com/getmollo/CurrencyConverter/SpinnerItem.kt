package com.getmollo.CurrencyConverter

import androidx.room.Entity
import androidx.room.PrimaryKey

//This is our SpinnerItem class annotated with entity so room creates an SQLlite DB Table for it
@Entity(tableName = "currency_table")
data class SpinnerItem(
    @PrimaryKey(autoGenerate = false)
    var currencyTicker:String,
    var currencyLogo:Int,
    var fromEurValue:Double
)

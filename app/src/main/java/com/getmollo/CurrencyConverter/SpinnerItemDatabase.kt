package com.getmollo.CurrencyConverter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(SpinnerItem::class), version = 1, exportSchema = false)
public abstract class SpinnerItemDatabase : RoomDatabase() {

    abstract fun wordDao(): SpinnerItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SpinnerItemDatabase? = null

        fun getDatabase(context: Context): SpinnerItemDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpinnerItemDatabase::class.java,
                    "currencies_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
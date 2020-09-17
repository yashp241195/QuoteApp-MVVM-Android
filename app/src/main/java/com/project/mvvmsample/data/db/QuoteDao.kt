package com.project.mvvmsample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.mvvmsample.data.db.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes: List<Quote>)

    @Query("SELECT * from Quote")
    fun getQuotes() : LiveData<List<Quote>>

}
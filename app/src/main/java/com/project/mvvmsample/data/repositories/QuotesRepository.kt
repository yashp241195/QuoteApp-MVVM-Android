package com.project.mvvmsample.data.repositories

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.mvvmsample.data.db.AppDatabase
import com.project.mvvmsample.data.db.entities.Quote
import com.project.mvvmsample.data.network.MyApi
import com.project.mvvmsample.data.network.SafeApiRequest
import com.project.mvvmsample.data.preferences.PreferenceProvider
import com.project.mvvmsample.ui.Utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_TIME_MINUTES = 10

class QuotesRepository(
    private val api: MyApi
    , private val db: AppDatabase,
    private val prefs: PreferenceProvider
    ) : SafeApiRequest(){

    private val quotes = MutableLiveData<List<Quote>>()

    init{
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes:List<Quote>){

        Coroutines.io {
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }

    suspend fun getQuotes() : LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes(){

        val lastSavedAt = prefs.getLastSavedAt()

        if(lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime) : Boolean{
        return ChronoUnit.MINUTES.between(savedAt,LocalDateTime.now()) > MINIMUM_TIME_MINUTES
    }



}



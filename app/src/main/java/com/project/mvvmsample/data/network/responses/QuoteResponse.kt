package com.project.mvvmsample.data.network.responses

import com.project.mvvmsample.data.db.entities.Quote

data class QuoteResponse (
    val isSuccessful:Boolean?,
    val message:String?,
    val quotes: List<Quote>?
)
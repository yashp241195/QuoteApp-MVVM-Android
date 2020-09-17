package com.project.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.project.mvvmsample.data.repositories.QuotesRepository
import com.project.mvvmsample.ui.Utils.lazyDeferred

class QuotesViewModel(
    quotesRepository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        quotesRepository.getQuotes()
    }

}
package com.project.mvvmsample.ui.home.quotes

import com.project.mvvmsample.R
import com.project.mvvmsample.data.db.entities.Quote
import com.project.mvvmsample.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote:Quote): BindableItem<ItemQuoteBinding>()
{
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.quoteDB = quote
    }

    override fun getLayout() = R.layout.item_quote

}
package com.project.mvvmsample.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mvvmsample.R
import com.project.mvvmsample.data.db.entities.Quote
import com.project.mvvmsample.ui.Utils.Coroutines
import com.project.mvvmsample.ui.Utils.hide
import com.project.mvvmsample.ui.Utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.quotes_fragment,container,false)

     }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this,factory).get(QuotesViewModel::class.java)

//        Coroutines.main {
//            val quotes = viewModel.quotes.await()
//            quotes.observe(viewLifecycleOwner, Observer {
//                context?.toast("Hey "+it.size.toString())
//            })
//        }

        bindUI()

    }


    private fun bindUI() = Coroutines.main {
        progressBar3.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progressBar3.hide()
            initRecyclerView(it.toItemQuote())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerViewQuote.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Quote>.toItemQuote():List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }


}
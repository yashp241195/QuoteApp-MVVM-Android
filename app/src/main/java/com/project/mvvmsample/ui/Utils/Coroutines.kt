package com.project.mvvmsample.ui.Utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

    // higher order function

    fun main(work : suspend (()->Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun io(work : suspend (()->Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}
package com.project.mvvmsample.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.project.mvvmsample.ui.Utils.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetExceptions("Make sure active internet connection is available")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean{

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetwork != null
    }
}
package com.project.mvvmsample.data.network

import com.project.mvvmsample.data.network.responses.AuthResponse
import com.project.mvvmsample.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(
        @Field("name") name: String,
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes(): Response<QuoteResponse>

// before coroutine optimisation
//    @FormUrlEncoded
//    @POST("login")
//    fun userLogin(
//        @Field("email") email : String,
//        @Field("password") password : String
//    ) : Call<ResponseBody>



    companion object{

        private const val baseUrl = "http://192.168.100.8:3000"



        operator fun invoke(
            nci: NetworkConnectionInterceptor
        )
                : MyApi{

            val okHttpClient = OkHttpClient.Builder().
                            addInterceptor(nci).build()


            return Retrofit.Builder().
                client(okHttpClient).
                baseUrl(baseUrl).
                addConverterFactory(
                    GsonConverterFactory.create()).
                build().create(MyApi::class.java)

        }
    }
}


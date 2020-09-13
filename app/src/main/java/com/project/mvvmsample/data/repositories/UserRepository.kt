package com.project.mvvmsample.data.repositories

import com.project.mvvmsample.data.db.AppDatabase
import com.project.mvvmsample.data.db.entities.User
import com.project.mvvmsample.data.network.MyApi
import com.project.mvvmsample.data.network.SafeApiRequest
import com.project.mvvmsample.data.network.responses.AuthResponse
import retrofit2.Response


class UserRepository(
    private val api: MyApi
    , private val db: AppDatabase
    ) : SafeApiRequest(){

    suspend fun userLogin(email:String, password:String):AuthResponse{
        return apiRequest { api.userLogin(email,password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()


}


// before fixing dependencies

//class UserRepository:SafeApiRequest() {
//
//    suspend fun userLogin(email:String, password:String):AuthResponse {
//        return apiRequest {  MyApi().userLogin(email, password)}
//    }

    // before SafeApiRequest

//    class UserRepository {
//
//        suspend fun userLogin(email:String, password:String):Response<AuthResponse> {
//
//            return MyApi().userLogin(email, password)
//
//
//        }
//


//
//    fun userLogin(email:String, password:String):LiveData<String> {
//
//        val loginResponse = MutableLiveData<String>()
//
//        Log.d("Login Inpute", email+" "+password);
//
//        MyApi().userLogin(email, password).enqueue(
//            object : Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if(response.isSuccessful){
//                        loginResponse.value = response.body()?.string()
//                    }else{
//                        loginResponse.value = response.errorBody()?.string()
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    loginResponse.value = t.message
//                }
//            }
//        )
//
//        return loginResponse
//    }

//}
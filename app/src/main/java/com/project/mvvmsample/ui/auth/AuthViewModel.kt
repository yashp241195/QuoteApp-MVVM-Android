package com.project.mvvmsample.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.project.mvvmsample.data.db.entities.User
import com.project.mvvmsample.data.repositories.UserRepository
import com.project.mvvmsample.ui.Utils.ApiExceptions
import com.project.mvvmsample.ui.Utils.Coroutines
import com.project.mvvmsample.ui.Utils.NoInternetExceptions

class AuthViewModel(
    private val userRepository: UserRepository
):
    ViewModel() {
    var email: String?  = null
    var password: String? = null

    var authListener : AuthListener? = null

    fun getUserLoggedIn() = userRepository.getUser()

    fun onLoginButtonCLick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        // success

        Coroutines.main {

            try {
                val loginResponse = userRepository.userLogin(email!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)

                    // save user to db as well
                    userRepository.saveUser(it)
                    return@main
                }

            }catch (e:ApiExceptions){
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetExceptions){
                authListener?.onFailure(e.message!!)
            }
//            before safeApiRequest
//            if(loginResponse.isSuccessful){
//                authListener?.onSuccess(loginResponse.body()?.user!!)
//            }else{
//                authListener?.onFailure("Error : ${loginResponse.code()}")
//            }

        }

        // before co routine optimisation
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListener?.onSuccess(loginResponse)
    }
}
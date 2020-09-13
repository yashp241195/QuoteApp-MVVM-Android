package com.project.mvvmsample.ui.auth

import androidx.lifecycle.LiveData
import com.project.mvvmsample.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
//    before coroutine optimisation
//    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message:String)
}
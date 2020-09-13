package com.project.mvvmsample.data.network.responses

import com.project.mvvmsample.data.db.entities.User

data class AuthResponse (
    val isSuccessful:Boolean?,
    val message:String?,
    val user: User?
)
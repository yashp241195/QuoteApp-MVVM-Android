package com.project.mvvmsample.ui.home.profile

import androidx.lifecycle.ViewModel
import com.project.mvvmsample.data.repositories.UserRepository

class ProfileViewModel(
    userRepository:UserRepository
) : ViewModel() {
    val user = userRepository.getUser()
}
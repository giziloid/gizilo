package com.aplikasi.gizilo.view.welcome

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository

class WelcomeViewModel(private val repository: UserRepository) : ViewModel() {
    fun getAuthToken() = repository.getAuthToken()
    fun getLoginSession() = repository.getLogin()
}
package com.aplikasi.gizilo.view.login

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository

class LoginViewModel(private val repository: UserRepository):ViewModel() {
    fun login(email:String,password:String) = repository.login(email,password)

    suspend fun saveAuthToken(token:String) = repository.saveAuthToken(token)
    suspend fun  saveLogin(loginSession:Boolean) = repository.saveLogin(loginSession)
}
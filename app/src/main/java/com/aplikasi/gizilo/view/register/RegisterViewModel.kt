package com.aplikasi.gizilo.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository
import com.aplikasi.gizilo.data.repository.Result
import com.aplikasi.gizilo.data.response.RegisterResponse

class RegisterViewModel(private val repository: UserRepository):ViewModel() {
    fun register(name:String,email:String,password:String): LiveData<Result<RegisterResponse>> = repository.register(name,email,password)
}
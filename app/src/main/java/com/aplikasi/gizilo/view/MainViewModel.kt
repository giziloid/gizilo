package com.aplikasi.gizilo.view

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository

class MainViewModel (private val repository: UserRepository):ViewModel(){
    suspend fun logout() = repository.logout()
}
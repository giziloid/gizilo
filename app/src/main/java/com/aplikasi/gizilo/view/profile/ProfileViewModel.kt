package com.aplikasi.gizilo.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository

class ProfileViewModel(private val repository: UserRepository):ViewModel(){
    suspend fun logout() = repository.logout()
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}
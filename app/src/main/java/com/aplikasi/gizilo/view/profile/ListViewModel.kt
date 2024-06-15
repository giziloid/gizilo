package com.aplikasi.gizilo.view.profile

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository

class ListViewModel(private val repository: UserRepository):ViewModel() {
    fun getProduct() = repository.getProducts()
}
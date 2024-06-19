package com.aplikasi.gizilo.view.profile

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository

class ProductViewModel(private val repository: UserRepository) : ViewModel() {
    fun getAllProduct() = repository.getProducts()
}
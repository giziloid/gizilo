package com.aplikasi.gizilo.view.result

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository


class ResultViewModel(private val repository: UserRepository) : ViewModel() {
    fun getProductGrade(name: String) = repository.getProductGrade(name)

}
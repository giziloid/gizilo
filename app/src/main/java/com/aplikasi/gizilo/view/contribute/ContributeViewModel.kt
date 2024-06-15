package com.aplikasi.gizilo.view.contribute

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository
import java.io.File

class ContributeViewModel(private val repository: UserRepository):ViewModel() {
    fun addProduct(images: File,
                   name: String,
                   calories: String,
                   totalFat: String,
                   protein: String,
                   carbohydrates: String,
                   sugar: String,
                   sodium: String) = repository.addProduct(images,name,calories,totalFat,protein,carbohydrates,sugar,sodium)
}
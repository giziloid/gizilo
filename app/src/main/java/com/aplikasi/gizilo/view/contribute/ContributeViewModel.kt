package com.aplikasi.gizilo.view.contribute

import androidx.lifecycle.ViewModel
import com.aplikasi.gizilo.data.pref.UserRepository
import java.io.File

class ContributeViewModel(private val repository: UserRepository) : ViewModel() {
    fun addProduct(
        images: File,
        name: String,
        calories: String,
        fat: String,
        proteins: String,
        carbohydrate: String,
        sugar: String,
        sodium: String,
        weight: String, potassium: String
    ) = repository.addProduct(
        images,
        name,
        calories,
        fat,
        proteins,
        carbohydrate,
        sugar,
        sodium,
        weight,
        potassium
    )
}
package com.aplikasi.gizilo.di

import android.content.Context
import com.aplikasi.gizilo.data.pref.UserPreference
import com.aplikasi.gizilo.data.pref.UserRepository
import com.aplikasi.gizilo.data.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getAuthToken().first() }
        val apiService = ApiConfig.getApiService(user)
        return UserRepository.getInstance(apiService,pref)
    }
}
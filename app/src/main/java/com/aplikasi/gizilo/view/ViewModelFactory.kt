package com.aplikasi.gizilo.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aplikasi.gizilo.data.pref.UserRepository
import com.aplikasi.gizilo.di.Injection
import com.aplikasi.gizilo.view.contribute.ContributeViewModel
import com.aplikasi.gizilo.view.login.LoginViewModel
import com.aplikasi.gizilo.view.profile.ListViewModel
import com.aplikasi.gizilo.view.register.RegisterViewModel
import com.aplikasi.gizilo.view.welcome.WelcomeViewModel

class ViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(WelcomeViewModel::class.java)->{
                WelcomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ListViewModel::class.java)->{
                ListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ContributeViewModel::class.java)->{
                ContributeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun clearInstance(){
            INSTANCE = null
            UserRepository.clearDataFactory()
        }

        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}
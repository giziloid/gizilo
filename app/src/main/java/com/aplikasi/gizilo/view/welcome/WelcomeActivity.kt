package com.aplikasi.gizilo.view.welcome

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aplikasi.gizilo.databinding.ActivityWelcomeBinding
import com.aplikasi.gizilo.view.MainActivity
import com.aplikasi.gizilo.view.ViewModelFactory
import com.aplikasi.gizilo.view.login.LoginActivity
import com.aplikasi.gizilo.view.register.RegisterActivity
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding
    private val viewModel : WelcomeViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()
        isLogin()
    }

    private fun setupAction(){
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun isLogin(){
        lifecycleScope.launch {
            viewModel.getAuthToken().combine(viewModel.getLoginSession()){token,session ->
                token to session
            }.collect{(token,session)->
                if (token !=null && session){
                    binding.btnLogin.visibility = View.GONE
                    binding.btnRegister.visibility = View.GONE
                    binding.imageView11.visibility = View.GONE
                    binding.titleWelcome.visibility = View.GONE
                    binding.descWelcome.visibility = View.GONE
                    startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}
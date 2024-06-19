package com.aplikasi.gizilo.view.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.gizilo.databinding.ActivityAboutUsBinding
import com.aplikasi.gizilo.view.profile.ProfileFragment

class AboutUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView (binding.root)

        binding.btnBackAboutUs.setOnClickListener {
            startActivity(Intent(this,ProfileFragment::class.java))
        }
    }
}
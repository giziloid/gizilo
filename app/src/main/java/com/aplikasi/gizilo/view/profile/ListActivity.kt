package com.aplikasi.gizilo.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aplikasi.gizilo.R
import android.util.Log
import android.widget.Toast
import com.aplikasi.gizilo.databinding.ActivityListBinding
import com.aplikasi.gizilo.view.contribute.ContributeActivity

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Log statement for debugging
        Log.d("ListActivity", "onCreate called")

        // Toast for debugging
        Toast.makeText(this, "ListActivity Started", Toast.LENGTH_SHORT).show()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, ContributeActivity::class.java))
        }
    }
}

package com.aplikasi.gizilo.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aplikasi.gizilo.R
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.gizilo.data.repository.Result
import com.aplikasi.gizilo.data.response.GetProductResponseItem
import com.aplikasi.gizilo.databinding.ActivityProductBinding
import com.aplikasi.gizilo.view.ViewModelFactory
import com.aplikasi.gizilo.view.adapter.ProductAdapter
import com.aplikasi.gizilo.view.contribute.ContributeActivity

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private val viewModel by viewModels<ProductViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvStory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvStory.addItemDecoration(itemDecoration)

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, ContributeActivity::class.java))
        }
        observeData()

        binding.icArrow.setOnClickListener {
            startActivity(Intent(this, ProfileFragment::class.java))
        }
    }

    private fun observeData() {
        viewModel.getAllProduct().observe(this) { list ->
            handleList(list)
        }
    }

    private fun handleList(list: Result<List<GetProductResponseItem>>) {
        when (list) {
            is Result.Success -> {
                binding.Proggressbar.visibility = View.GONE
                val adapter = ProductAdapter()
                binding.rvStory.adapter = adapter
                adapter.submitList(list.data)
            }

            is Result.Error -> {
                binding.Proggressbar.visibility = View.GONE
                Toast.makeText(this, "Error : ${list.error}", Toast.LENGTH_SHORT).show()
            }

            is Result.Loading -> {
                binding.Proggressbar.visibility = View.VISIBLE
            }
        }
    }
}

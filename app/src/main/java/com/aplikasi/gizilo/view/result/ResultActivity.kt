package com.aplikasi.gizilo.view.result

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.gizilo.R
import com.aplikasi.gizilo.data.repository.Result
import com.aplikasi.gizilo.databinding.ActivityResultBinding
import com.aplikasi.gizilo.utils.ImageClassifierHelper
import com.aplikasi.gizilo.view.ViewModelFactory
import org.tensorflow.lite.task.vision.classifier.Classifications

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val viewModel by viewModels<ResultViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val imageUri = Uri.parse(intent.getStringExtra(IMAGE_URI))
        imageUri?.let {
            binding.resultImage.setImageURI(it)
        }
        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val imageClassifierHelper = ImageClassifierHelper(
            context = this,
            classifierListener = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(error: String) {
                    Log.d("photoGallery", "ShowImage : $imageUri")
                    Toast.makeText(this@ResultActivity, "Error : $error", Toast.LENGTH_SHORT).show()
                }

                @SuppressLint("SetTextI18n")
                override fun onResult(result: List<Classifications>?, inference: Long) {
                    result?.let {
                        if (it.isNotEmpty() && it[0].categories.isNotEmpty()) {
                            val topResult = result[0]
                            val label = topResult.categories[0].label
                            val score = topResult.categories[0].score

                            @SuppressLint("DefaultLocale")
                            fun Float.formatToString(): String {
                                return String.format("%.2f%%", this * 100)
                            }
                            binding.resultText.text = "$label \n${score.formatToString()}"
                            fetchProductGrade(label)
                        } else {
                            Toast.makeText(
                                this@ResultActivity,
                                "Tidak ada hasil klasifikasi yang ditemukan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        )
        imageClassifierHelper.classifyImage(imageUri)
    }

    private fun fetchProductGrade(result: String) {
        viewModel.getProductGrade(result).observe(this) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    binding.resultText.append("\nGrade: ${it.data.result}")
                    it.data.result?.let { it1 -> displayGradeImage(it1) }
                    it.data.result?.let { it1 -> displayDescResult(it1) }
                }

                is Result.Error -> {
                    Toast.makeText(this, "Error: ${it.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun displayGradeImage(grade:String){
        val drawables = when(grade){
            "A"-> R.drawable.ic_result_a
            "B"-> R.drawable.ic_result_b
            "C"-> R.drawable.ic_result_c
            "D"->R.drawable.ic_result_d
            else -> Toast.makeText(this,"tidak ada grade",Toast.LENGTH_SHORT).show()
        }
        binding.ivResult.setImageResource(drawables as Int)
    }


    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun displayDescResult(grade: String) {
        val values = when (grade) {
            "A" -> R.string.result_a
            "B" -> R.string.result_b
            "C" -> R.string.result_c
            "D" -> R.string.result_d
            else -> Toast.makeText(this, "tidak ada grade", Toast.LENGTH_SHORT).show()
        }
        values.let {
            binding.tvResult.text = resources.getString(it as Int)
        }
    }

    companion object {
        const val IMAGE_URI = "image_uri"
    }
}
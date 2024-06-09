package com.aplikasi.gizilo.view.camera

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplikasi.gizilo.databinding.FragmentCameraBinding
import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        isGranted: Boolean ->
        if (isGranted){
            showToast("Permission request granted")
        }else{
            showToast("Permission request denied")
        }
    }
    private fun allPermissionGranted() = ContextCompat.checkSelfPermission(
        requireActivity(),
        REQUIRED_PERMISSION
    ) == PackageManager.PERMISSION_GRANTED

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cameraViewModel =
            ViewModelProvider(this)[CameraViewModel::class.java]

        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (!allPermissionGranted()){
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }else{

        }

        return root
    }
    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}
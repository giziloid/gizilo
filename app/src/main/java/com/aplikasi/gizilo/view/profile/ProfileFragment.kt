package com.aplikasi.gizilo.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aplikasi.gizilo.databinding.FragmentProfileBinding
import com.aplikasi.gizilo.view.ViewModelFactory
import com.aplikasi.gizilo.view.settings.AboutUsActivity
import com.aplikasi.gizilo.view.welcome.WelcomeActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.aboutBtn.setOnClickListener {
            val intent = Intent(requireActivity(), AboutUsActivity::class.java)
            startActivity(intent)
        }
        binding.btnLainnya.setOnClickListener {
            startActivity(Intent(requireActivity(), ProductActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
              viewModel.logout()
            }
            startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
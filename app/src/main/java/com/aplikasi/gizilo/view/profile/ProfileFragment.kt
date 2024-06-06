package com.aplikasi.gizilo.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aplikasi.gizilo.R
import com.aplikasi.gizilo.databinding.FragmentProfileBinding
import com.aplikasi.gizilo.view.login.LoginActivity
import com.aplikasi.gizilo.view.settings.AboutUsActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var auth: FirebaseAuth

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.to_bottom_anim
        )
    }


    private var clicked = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        if (firebaseUser != null) {
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }
        binding.settingsButton.setOnClickListener {
            settingButtonClicked()
        }
        binding.aboutBtn.setOnClickListener {
            val intent = Intent(requireActivity(), AboutUsActivity::class.java)
            startActivity(intent)
        }
        binding.logoutBtn.setOnClickListener {
          signOut()
        }
        return root
    }

    private fun settingButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.aboutBtn.startAnimation(fromBottom)
            binding.logoutBtn.startAnimation(fromBottom)
            binding.settingsButton.startAnimation(rotateOpen)
        } else {
            binding.aboutBtn.startAnimation(toBottom)
            binding.logoutBtn.startAnimation(toBottom)
            binding.settingsButton.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.aboutBtn.visibility = View.VISIBLE
            binding.logoutBtn.visibility = View.VISIBLE
        } else {
            binding.aboutBtn.visibility = View.INVISIBLE
            binding.logoutBtn.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            binding.aboutBtn.isClickable = false
            binding.logoutBtn.isClickable = false
        } else {
            binding.aboutBtn.isClickable = true
            binding.logoutBtn.isClickable = true
        }
    }
    private fun signOut(){
        lifecycleScope.launch {
            val credentialManager =  CredentialManager.create(requireActivity())
            auth.signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
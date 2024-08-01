package com.amineaytac.redplanetgallery.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.amineaytac.redplanetgallery.databinding.FragmentSplashBinding
class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding  =FragmentSplashBinding.inflate(layoutInflater,container,false)

        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment2()
            findNavController().navigate(action)
        },4000)

        return binding.root
    }
}
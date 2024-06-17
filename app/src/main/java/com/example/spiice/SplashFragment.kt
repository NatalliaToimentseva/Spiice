package com.example.spiice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.screens.account.LogInFragment
import com.example.spiice.databinding.FragmentSplashBinding
import com.example.spiice.screens.onboarding.ViewPagerFragment
import com.example.spiice.utils.createSpanForView

class SplashFragment : Fragment() {

    private var binding: FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginFromSplashScreenButton?.let { createSpanForView(it) }

        binding?.splashScreenSingUpButton?.setOnClickListener {
            navigator().startFragment(ViewPagerFragment())
        }

        binding?.loginFromSplashScreenButton?.setOnClickListener {
            navigator().startFragment(LogInFragment())
        }
    }
}
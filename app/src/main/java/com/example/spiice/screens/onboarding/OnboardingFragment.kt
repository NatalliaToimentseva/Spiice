package com.example.spiice.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.spiice.databinding.FragmentOnboardingBinding
import com.example.spiice.navigator
import com.example.spiice.screens.account.SignUpFragment

const val ONB_TEXT = "onboarding text"
const val ONB_IMAGE = "onboarding image"
const val ONB_BG = "onboarding background"

class OnboardingFragment : Fragment() {

    private var binding: FragmentOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        binding?.run {
            arguments?.apply {
                onbText.text = getString(getInt(ONB_TEXT))
                onbImage.setImageResource(getInt(ONB_IMAGE))
                onboarding.setBackgroundResource((getInt(ONB_BG)))
            }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.onbSkipButton?.setOnClickListener {
            navigator().cancelFragment()
            navigator().startFragment(SignUpFragment())
        }
    }

    companion object {
        fun getFragment(text: Int, image: Int, bg: Int): OnboardingFragment {
            return OnboardingFragment().apply {
                arguments = bundleOf(ONB_TEXT to text, ONB_IMAGE to image, ONB_BG to bg)
            }
        }
    }
}
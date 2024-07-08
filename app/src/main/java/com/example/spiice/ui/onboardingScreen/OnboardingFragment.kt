package com.example.spiice.ui.onboardingScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.spiice.R
import com.example.spiice.databinding.FragmentOnboardingBinding
import com.example.spiice.navigator.navigator
import com.example.spiice.ui.signUpScreen.SignUpFragment

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
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            arguments?.apply {
                onbText.text = getString(getInt(ONB_TEXT))
                onbImage.setImageResource(getInt(ONB_IMAGE))
                onboarding.setBackgroundResource((getInt(ONB_BG)))
            }
        }
        binding?.onbSkipButton?.setOnClickListener {
            navigator().cancelFragment()
            navigator().startFragment(SignUpFragment())
        }
    }

    companion object {
        fun getFirstStep(): OnboardingFragment {
            return OnboardingFragment().apply {
                arguments = bundleOf(
                    ONB_TEXT to R.string.onb_text_1,
                    ONB_IMAGE to R.drawable.onb_planet,
                    ONB_BG to R.drawable.bg_onboarding_first
                )
            }
        }

        fun getSecondStep(): OnboardingFragment {
            return OnboardingFragment().apply {
                arguments = bundleOf(
                    ONB_TEXT to R.string.onb_text_2,
                    ONB_IMAGE to R.drawable.onb_arrow,
                    ONB_BG to R.drawable.bg_onboarding_second
                )
            }
        }

        fun getThirdStep(): OnboardingFragment {
            return OnboardingFragment().apply {
                arguments = bundleOf(
                    ONB_TEXT to R.string.onb_text_3,
                    ONB_IMAGE to R.drawable.onb_chat,
                    ONB_BG to R.drawable.bg_onboarding_third
                )
            }
        }
    }
}
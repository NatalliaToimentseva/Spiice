package com.example.spiice.screens.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spiice.R

class NoteViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        var fragment: OnboardingFragment? = null
        when (position) {
            0 -> fragment = OnboardingFragment.getFragment(
                R.string.onb_text_1,
                R.drawable.onb_planet,
                R.drawable.bg_onboarding_first
            )

            1 -> fragment = OnboardingFragment.getFragment(
                R.string.onb_text_2,
                R.drawable.onb_arrow,
                R.drawable.bg_onboarding_second
            )

            2 -> fragment = OnboardingFragment.getFragment(
                R.string.onb_text_3,
                R.drawable.onb_chat,
                R.drawable.bg_onboarding_third
            )
        }
        return fragment ?: OnboardingFragment()
    }
}
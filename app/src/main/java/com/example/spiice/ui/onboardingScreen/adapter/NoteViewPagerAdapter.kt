package com.example.spiice.ui.onboardingScreen.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spiice.ui.onboardingScreen.OnboardingFragment

const val FR_NUMBERS = 3

class NoteViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = FR_NUMBERS
    override fun createFragment(position: Int): Fragment {
        var fragment: OnboardingFragment? = null
        when (position) {
            0 -> fragment = OnboardingFragment.getFirstStep()

            1 -> fragment = OnboardingFragment.getSecondStep()

            2 -> fragment = OnboardingFragment.getThirdStep()
        }
        return fragment ?: OnboardingFragment()
    }
}
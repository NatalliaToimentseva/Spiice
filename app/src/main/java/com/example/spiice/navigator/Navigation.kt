package com.example.spiice.navigator

import androidx.fragment.app.Fragment

interface Navigation {

    fun startFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment)

    fun cancelFragment()

    fun goBack()
}

fun Fragment.navigator(): Navigation {
    return requireActivity() as Navigation
}
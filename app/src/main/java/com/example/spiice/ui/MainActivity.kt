package com.example.spiice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.spiice.App
import com.example.spiice.R
import com.example.spiice.databinding.ActivityMainBinding
import com.example.spiice.navigator.Navigation
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.logInScreen.LogInFragment
import com.example.spiice.ui.navigationContainer.NavigationFragment
import com.example.spiice.ui.splashScreen.SplashFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Navigation {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        App.appComponent?.inject(this)

        if (savedInstanceState == null) {
            replaceFragment(chooseStartFragment())
        }
    }

    private fun chooseStartFragment(): Fragment {
        val userId = sharedPreferencesRepository.getEmail()
        return if (sharedPreferencesRepository.isFirstLaunch()) {
            SplashFragment()
        } else if (userId != null) {
            NavigationFragment()
        } else LogInFragment()
    }

    override fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun cancelFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun goToStart() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun logout() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            goToStart()
        } else {
            goToStart()
            startFragment(LogInFragment())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) finish()
    }
}
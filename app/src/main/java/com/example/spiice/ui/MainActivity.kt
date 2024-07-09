package com.example.spiice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spiice.R
import com.example.spiice.databinding.ActivityMainBinding
import com.example.spiice.ui.splashScreen.SplashFragment
import com.example.spiice.navigator.Navigation
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.logInScreen.LogInFragment
import com.example.spiice.ui.notesListScreen.NotesListFragment

class MainActivity : AppCompatActivity(), Navigation {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, chooseStartFragment())
                .commit()
        }
    }

    private fun chooseStartFragment(): Fragment {
        val userId = SharedPreferencesRepository.getEmail()
        return if (SharedPreferencesRepository.isFirstLaunch()) {
            SplashFragment()
        } else if (userId != null) {
            NotesListFragment.getFragment(userId)
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

    override fun cancelFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun goBack() {
        onBackPressed()
    }
}
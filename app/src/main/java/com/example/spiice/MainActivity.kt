package com.example.spiice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spiice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigation {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SplashFragment())
            .commit()
    }

    override fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun cancelFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun goBack() {
        onBackPressed()
    }
}
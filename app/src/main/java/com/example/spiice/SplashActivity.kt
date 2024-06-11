package com.example.spiice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spiice.account.LogInActivity
import com.example.spiice.account.SignUpActivity
import com.example.spiice.databinding.ActivitySplashBinding
import com.example.spiice.utils.createSpanForView

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.loginFromSplashScreenButton?.let { createSpanForView(it) }

        binding?.splashScreenSingUpButton?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding?.loginFromSplashScreenButton?.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
}
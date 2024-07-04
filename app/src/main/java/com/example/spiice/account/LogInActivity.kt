package com.example.spiice.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.spiice.databinding.ActivityLogInBinding
import com.example.spiice.notes.screen.NotesListActivity
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emptyFieldValidation
import com.example.spiice.validations.fieldHandler

class LogInActivity : AppCompatActivity() {

    private var binding: ActivityLogInBinding? = null
    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLogInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.run {
            loginScreen.setOnClickListener {
                if (it.hasFocus()) {
                    emailLogLayout.clearFocus()
                    passwordLogLayout.clearFocus()
                }
            }
        }

        binding?.run {
            emailLogET.doAfterTextChanged {
                isEmailValid = fieldHandler(
                    emailLogET,
                    emailLogLayout,
                    emptyFieldValidation(it.toString())
                )
                logInButton.isEnabled = activateButton(isEmailValid, isPasswordValid)
            }
        }

        binding?.run {
            passwordLogET.doAfterTextChanged {
                isPasswordValid = fieldHandler(
                    passwordLogET,
                    passwordLogLayout,
                    emptyFieldValidation(it.toString())
                )
                logInButton.isEnabled = activateButton(isEmailValid, isPasswordValid)
            }
        }

        binding?.logInButton?.setOnClickListener {
            startActivity(Intent(this, NotesListActivity::class.java))
        }

        binding?.singUpFromLoginScreenButton?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
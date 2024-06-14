package com.example.spiice.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.spiice.databinding.ActivitySignUpBinding
import com.example.spiice.utils.createSpanForView
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emailValidator
import com.example.spiice.validations.fieldHandler
import com.example.spiice.validations.nameValidator
import com.example.spiice.validations.passwordValidator

class SignUpActivity : AppCompatActivity() {

    private var isValidFirstName = false
    private var isValidLastName = false
    private var isValidEmail = false
    private var isValidPassword = false
    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.loginFromSignUpScreenButton?.let { createSpanForView(it) }

        binding?.run {
            signUpScreen.setOnClickListener {
                if (it.hasFocus()) {
                    firstNameSingUpLayout.clearFocus()
                    lastNameSignUpLayout.clearFocus()
                    emailSignUpLayout.clearFocus()
                    passwordSignUpLayout.clearFocus()
                }
            }
        }

        binding?.run {
            firstNameSignUpET.doAfterTextChanged {
                isValidFirstName = fieldHandler(
                    firstNameSignUpET,
                    firstNameSingUpLayout,
                    nameValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.run {
            lastNameSignUpET.doAfterTextChanged {
                isValidLastName = fieldHandler(
                    lastNameSignUpET,
                    lastNameSignUpLayout,
                    nameValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.run {
            emailSignUpET.doAfterTextChanged {
                isValidEmail = fieldHandler(
                    emailSignUpET,
                    emailSignUpLayout,
                    emailValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.run {
            passwordSignUpET.doAfterTextChanged {
                isValidPassword = fieldHandler(
                    passwordSignUpET,
                    passwordSignUpLayout,
                    passwordValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.signUpButton?.setOnClickListener {
            //TODO
        }

        binding?.loginFromSignUpScreenButton?.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
}
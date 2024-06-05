package com.example.spiice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {

    private var isValidFirstName = false
    private var isValidLastName = false
    private var isValidEmail = false
    private var isValidPassword = false
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpButton = findViewById(R.id.sing_up_button)
        val logInButton = findViewById<TextView>(R.id.login_from_sing_up_screen_button)

        createSpanForView(logInButton)

        val firstNameEditText = findViewById<EditText>(R.id.first_name_sing_up)
        val lastNameEditText = findViewById<EditText>(R.id.last_name_sing_up)
        val emailEditText = findViewById<EditText>(R.id.email_sing_up)
        val passwordEditText = findViewById<EditText>(R.id.password_sing_up)
        val firstNameTextInputLayout = findViewById<TextInputLayout>(R.id.first_name_sing_up_layout)
        val lastNameTextInputLayout = findViewById<TextInputLayout>(R.id.last_name_sing_up_layout)
        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.email_sing_up_layout)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.password_sing_up_layout)

        firstNameEditText.doAfterTextChanged {
            if (!nameValidator(it.toString().trim())) {
                isValidFirstName = false
                firstNameTextInputLayout.error = getString(R.string.error_message_name)
                firstNameEditText.requestFocus()
            } else {
                isValidFirstName = true
                firstNameTextInputLayout.error = null
            }
            activateSignUpButton()
        }

        lastNameEditText.doAfterTextChanged {
            if (!nameValidator(it.toString().trim())) {
                isValidLastName = false
                lastNameTextInputLayout.error = getString(R.string.error_message_name)
                lastNameEditText.requestFocus()
            } else {
                isValidLastName = true
                lastNameTextInputLayout.error = null
            }
            activateSignUpButton()
        }

        emailEditText.doAfterTextChanged {
            if (!emailValidator(it.toString().trim())) {
                isValidEmail = false
                emailTextInputLayout.error = getString(R.string.error_message_email)
                emailEditText.requestFocus()
            } else {
                isValidEmail = true
                emailTextInputLayout.error = null
            }
            activateSignUpButton()
        }

        passwordEditText.doAfterTextChanged {
            if (!passwordValidator(it.toString().trim())) {
                isValidPassword = false
                passwordTextInputLayout.error = getString(R.string.error_message_password)
                passwordEditText.requestFocus()
            } else {
                isValidPassword = true
                passwordTextInputLayout.error = null
            }
            activateSignUpButton()
        }

        signUpButton.setOnClickListener {
            //TODO
        }

        logInButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }

    private fun activateSignUpButton() {
        signUpButton.isEnabled =
            isValidFirstName && isValidLastName && isValidEmail && isValidPassword
    }
}
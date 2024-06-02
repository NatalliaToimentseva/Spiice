package com.example.spiice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.spiice.FieldValidators.emailValidator
import com.example.spiice.FieldValidators.nameValidator
import com.example.spiice.FieldValidators.passwordValidator
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private var isValidFirstName = false
    private var isValidLastName = false
    private var isValidEmail = false
    private var isValidPassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val singUpButton = findViewById<Button>(R.id.sing_up_button)
        val logInButton = findViewById<TextView>(R.id.login_from_sing_up_screen_button)
        createSpanForView(logInButton)

        val firstName = findViewById<EditText>(R.id.first_name_sing_up)
        val lastName = findViewById<EditText>(R.id.last_name_sing_up)
        val email = findViewById<EditText>(R.id.email_sing_up)
        val password = findViewById<EditText>(R.id.password_sing_up)
        val firstNameLayout = findViewById<TextInputLayout>(R.id.first_name_sing_up_layout)
        val lastNameLayout = findViewById<TextInputLayout>(R.id.last_name_sing_up_layout)
        val emailLayout = findViewById<TextInputLayout>(R.id.email_sing_up_layout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.password_sing_up_layout)

        firstName.doAfterTextChanged {
            if(!nameValidator(it.toString().trim())) {
                isValidFirstName = false
                firstNameLayout.error = getString(R.string.error_message_name)
                firstName.requestFocus()
            } else {
                isValidFirstName = true
                firstNameLayout.error = null
            }
            activateSignUpButton(singUpButton)
        }
        lastName.doAfterTextChanged {
            if(!nameValidator(it.toString().trim())) {
                isValidLastName = false
                lastNameLayout.error = getString(R.string.error_message_name)
                lastName.requestFocus()
            } else {
                isValidLastName = true
                lastNameLayout.error = null
            }
            activateSignUpButton(singUpButton)
        }
        email.doAfterTextChanged {
            if(!emailValidator(it.toString().trim())) {
                isValidEmail = false
                emailLayout.error = getString(R.string.error_message_email)
                email.requestFocus()
            } else {
                isValidEmail = true
                emailLayout.error = null
            }
            activateSignUpButton(singUpButton)
        }
        password.doAfterTextChanged {
            if(!passwordValidator(it.toString().trim())) {
                isValidPassword = false
                passwordLayout.error = getString(R.string.error_message_password)
                password.requestFocus()
            } else {
                isValidPassword = true
                passwordLayout.error = null
            }
            activateSignUpButton(singUpButton)
        }

        singUpButton.setOnClickListener{
            //TODO
        }

        logInButton.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
    private fun activateSignUpButton(button: Button) {
        if (isValidFirstName&& isValidLastName && isValidEmail && isValidPassword) {
            button.isEnabled = true
        } else button.isEnabled = false
    }
}
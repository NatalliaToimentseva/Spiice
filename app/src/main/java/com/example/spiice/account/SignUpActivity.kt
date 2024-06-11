package com.example.spiice.account

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.example.spiice.R
import com.example.spiice.utils.createSpanForView
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emailValidator
import com.example.spiice.validations.fieldHandler
import com.example.spiice.validations.nameValidator
import com.example.spiice.validations.passwordValidator
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

        val rootSignUpCL = findViewById<ConstraintLayout>(R.id.sign_up_screen)
        val firstNameEditText = findViewById<EditText>(R.id.first_name_sing_up)
        val lastNameEditText = findViewById<EditText>(R.id.last_name_sing_up)
        val emailEditText = findViewById<EditText>(R.id.email_sing_up)
        val passwordEditText = findViewById<EditText>(R.id.password_sing_up)
        val firstNameTextInputLayout = findViewById<TextInputLayout>(R.id.first_name_sing_up_layout)
        val lastNameTextInputLayout = findViewById<TextInputLayout>(R.id.last_name_sing_up_layout)
        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.email_sing_up_layout)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.password_sing_up_layout)

        rootSignUpCL.setOnClickListener {
            if (it.hasFocus()) {
                firstNameTextInputLayout.clearFocus()
                lastNameTextInputLayout.clearFocus()
                emailTextInputLayout.clearFocus()
                passwordTextInputLayout.clearFocus()
            }
        }

        firstNameEditText.doAfterTextChanged {
            isValidFirstName = fieldHandler(
                firstNameEditText,
                firstNameTextInputLayout,
                nameValidator(it.toString().trim())
            )
            signUpButton.isEnabled =
                activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
        }

        lastNameEditText.doAfterTextChanged {
            isValidLastName = fieldHandler(
                lastNameEditText,
                lastNameTextInputLayout,
                nameValidator(it.toString().trim())
            )
            signUpButton.isEnabled =
                activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
        }

        emailEditText.doAfterTextChanged {
            isValidEmail = fieldHandler(
                emailEditText,
                emailTextInputLayout,
                emailValidator(it.toString().trim())
            )
            signUpButton.isEnabled =
                activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
        }

        passwordEditText.doAfterTextChanged {
            isValidPassword = fieldHandler(
                passwordEditText,
                passwordTextInputLayout,
                passwordValidator(it.toString().trim())
            )
            signUpButton.isEnabled =
                activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
        }

        signUpButton.setOnClickListener {
            //TODO
        }

        logInButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
}
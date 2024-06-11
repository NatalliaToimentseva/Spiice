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
import com.example.spiice.notes.screen.NotesListActivity
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emptyFieldValidation
import com.example.spiice.validations.fieldHandler
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {

    private lateinit var logInButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        logInButton = findViewById(R.id.log_in_button)
        emailEditText = findViewById(R.id.email_log)
        passwordEditText = findViewById(R.id.password_log)
        val rootLoginCL = findViewById<ConstraintLayout>(R.id.login_screen)
        val singUpButton = findViewById<TextView>(R.id.sing_up_from_login_screen_button)
        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.email_log_layout)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.password_log_layout)

        rootLoginCL.setOnClickListener {
            if (it.hasFocus()) {
                emailTextInputLayout.clearFocus()
                passwordTextInputLayout.clearFocus()
            }
        }

        emailEditText.doAfterTextChanged {
            isEmailValid = fieldHandler(
                emailEditText,
                emailTextInputLayout,
                emptyFieldValidation(it.toString())
            )
            logInButton.isEnabled = activateButton(isEmailValid, isPasswordValid)
        }

        passwordEditText.doAfterTextChanged {
            isPasswordValid = fieldHandler(
                passwordEditText,
                passwordTextInputLayout,
                emptyFieldValidation(it.toString())
            )
            logInButton.isEnabled = activateButton(isEmailValid, isPasswordValid)
        }

        logInButton.setOnClickListener {
            startActivity(Intent(this, NotesListActivity::class.java))
        }

        singUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
package com.example.spiice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {

    private lateinit var logInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        logInButton = findViewById(R.id.log_in_button)
        val singUpButton = findViewById<TextView>(R.id.sing_up_from_login_screen_button)
        val emailEditText = findViewById<EditText>(R.id.email_log)
        val password = findViewById<EditText>(R.id.password_log)
        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.email_log_layout)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.password_log_layout)

        emailEditText.doAfterTextChanged { text ->
            if (text.toString().isBlank()) {
                emailTextInputLayout.error = getString(R.string.error_message_login)
                emailEditText.requestFocus()

            } else emailTextInputLayout.error = null
            activateLogInButton(emailEditText, password)
        }

        password.doAfterTextChanged { text ->
            if (text.toString().isBlank()) {
                passwordTextInputLayout.error = getString(R.string.error_message_login)
                password.requestFocus()
            } else passwordTextInputLayout.error = null
            activateLogInButton(emailEditText, password)
        }

        logInButton.setOnClickListener {
            //TODO
        }

        singUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun activateLogInButton(email: EditText, password: EditText) {
        logInButton.isEnabled =
            !(email.text.toString().isBlank() || password.text.toString().isBlank())
    }
}
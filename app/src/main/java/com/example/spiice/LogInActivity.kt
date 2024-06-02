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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val logInButton = findViewById<Button>(R.id.log_in_button)
        val singUpButton = findViewById<TextView>(R.id.sing_up_from_login_screen_button)
        val email = findViewById<EditText>(R.id.email_log)
        val password = findViewById<EditText>(R.id.password_log)
        val emailLayout = findViewById<TextInputLayout>(R.id.email_log_layout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.password_log_layout)

        email.doAfterTextChanged {text ->
            if(text.toString().isBlank()) {
                emailLayout.error = getString(R.string.error_message_login)
                email.requestFocus()
            } else emailLayout.error = null
            activateLogInButton(email, password, logInButton)
        }
        password.doAfterTextChanged {text ->
            if(text.toString().isBlank()) {
                passwordLayout.error = getString(R.string.error_message_login)
                password.requestFocus()
            } else passwordLayout.error = null
            activateLogInButton(email, password, logInButton)
        }

        logInButton.setOnClickListener{
            //TODO
        }

        singUpButton.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
    private fun activateLogInButton(email: EditText, password: EditText, logInButton: Button) {
        if (!(email.text.toString().isBlank() || password.text.toString().isBlank())) {
            logInButton.isEnabled = true
        } else logInButton.isEnabled = false
    }
}
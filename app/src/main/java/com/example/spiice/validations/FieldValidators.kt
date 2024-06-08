package com.example.spiice.validations

import android.util.Patterns
import android.widget.EditText
import androidx.core.content.ContextCompat.getString
import com.example.spiice.R
import com.google.android.material.textfield.TextInputLayout

const val MIN_NAME_LENGTH = 3
const val MAX_NAME_LENGTH = 255
const val MIN_PASSWORD_LENGTH = 6
const val MAX_PASSWORD_LENGTH = 50
const val PASSWORD_PATTERN = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*"

fun nameValidator(name: String): Boolean {
    return name.isNotBlank()
            && name.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH
}

fun passwordValidator(password: String): Boolean {
    return password.isNotBlank()
            && password.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH
            && password.matches(PASSWORD_PATTERN.toRegex())
}

fun emailValidator(email: String): Boolean {
    return email.isNotBlank()
            && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun emptyFieldValidation(text: String): Boolean {
    return text.isNotBlank()
}

fun fieldHandler(view: EditText, viewLayout: TextInputLayout, isValidated: Boolean): Boolean {
    val id = view.id
    val isValid: Boolean
    if (!isValidated) {
        viewLayout.error = when {
            (id == R.id.first_name_sing_up || id == R.id.last_name_sing_up) -> getString(view.context, R.string.error_message_name)
            (id == R.id.email_sing_up) -> getString(view.context, R.string.error_message_email)
            (id == R.id.password_sing_up) -> getString(view.context, R.string.error_message_password)
            else -> getString(view.context, R.string.error_message_empty_field)
        }
        isValid = false
        view.requestFocus()
    } else {
        viewLayout.error = null
        isValid = true
    }
    return isValid
}

fun activateButton(vararg screenFields: Boolean): Boolean {
    var count = 0
    for (field in screenFields) {
        if (!field) count++
    }
    return (count == 0)
}
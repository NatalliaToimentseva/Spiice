package com.example.spiice

import android.util.Patterns

object FieldValidators {

    fun nameValidator(name: String): Boolean {
        return name.isNotBlank()
                && name.length in 3..255
    }

    fun passwordValidator(password: String): Boolean {
        return password.isNotBlank()
                && password.length in 6..50
                && password.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*".toRegex())
    }

    fun emailValidator(email: String): Boolean {
        return email.isNotBlank()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
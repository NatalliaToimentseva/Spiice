package com.example.spiice

import android.util.Patterns

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
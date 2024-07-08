package com.example.spiice.entities.userEntity

sealed class User

data class LoginUser (
    val id: Long,
    val email: String,
    val password: String,
)

data class SignUpUser (
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
)
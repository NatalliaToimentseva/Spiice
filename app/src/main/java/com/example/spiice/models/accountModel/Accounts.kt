package com.example.spiice.models.accountModel

sealed class Account

data class LoginAccountData(
    val id: Long,
    val email: String,
    val password: String,
) : Account()

data class SignUpAccountData(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
) : Account()

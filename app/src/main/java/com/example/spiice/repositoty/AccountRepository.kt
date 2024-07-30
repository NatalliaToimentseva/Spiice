package com.example.spiice.repositoty

import com.example.spiice.models.accountModel.SignUpAccountData

interface AccountRepository {

    fun getAccount(email: String, password: String): String

    fun createAccount(signUpAccountData: SignUpAccountData)
}
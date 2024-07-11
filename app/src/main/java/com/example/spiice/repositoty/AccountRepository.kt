package com.example.spiice.repositoty

import com.example.spiice.models.accountModel.SignUpAccountData

interface AccountRepository {

    suspend fun getAccount(email: String, password: String): String

    suspend fun createAccount(signUpAccountData: SignUpAccountData)
}
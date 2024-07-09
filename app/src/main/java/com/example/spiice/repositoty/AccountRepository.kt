package com.example.spiice.repositoty

import com.example.spiice.models.accountModel.LoginAccountData

interface AccountRepository {

    fun getAccount(email: String, password: String): LoginAccountData

    fun createAccount(firstName: String, lastName: String, email: String, password: String)
}
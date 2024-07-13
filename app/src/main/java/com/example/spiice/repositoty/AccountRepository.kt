package com.example.spiice.repositoty

import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.roomDB.entities.UserTuple

interface AccountRepository {

    suspend fun getAccount(email: String, password: String): String

    suspend fun getUserName(email: String): UserTuple

    suspend fun createAccount(signUpAccountData: SignUpAccountData)

    suspend fun deleteAccountByEmail(email: String)
}
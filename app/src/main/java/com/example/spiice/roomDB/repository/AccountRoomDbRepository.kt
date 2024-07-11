package com.example.spiice.roomDB.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.roomDB.AccountAlreadyExistException
import com.example.spiice.roomDB.AuthException
import com.example.spiice.roomDB.DataBaseProvider
import com.example.spiice.roomDB.PasswordMismatchException
import com.example.spiice.utils.securityUtils.DefaultSecurityUtilsImpl
import com.example.spiice.utils.toAccountDBEntity

class AccountRoomDbRepository : AccountRepository {

    private val securityUtils = DefaultSecurityUtilsImpl()

    override suspend fun getAccount(email: String, password: String): String {
        val account = DataBaseProvider.accountDao?.getAccountByEmail(email)
            ?: throw AuthException("The password or email are incorrect!")
        val saltBytes = securityUtils.stringToBytes(account.salt)
        val hashPassword = securityUtils.passwordToHash(password.toCharArray(), saltBytes)
        val hashString = securityUtils.bytesToString(hashPassword)
        if (account.hash != hashString) throw PasswordMismatchException("The password or email are incorrect!")
        return account.email
    }

    override suspend fun createAccount(signUpAccountData: SignUpAccountData) {
        try {
            DataBaseProvider.accountDao?.createAccount(
                signUpAccountData.toAccountDBEntity()
            )
        } catch (e: SQLiteConstraintException) {
            val appException = AccountAlreadyExistException("Account already exist!")
            appException.initCause(e)
            throw appException
        }
    }
}
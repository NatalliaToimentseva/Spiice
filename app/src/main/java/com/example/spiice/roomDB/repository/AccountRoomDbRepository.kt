package com.example.spiice.roomDB.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.roomDB.AccountAlreadyExistException
import com.example.spiice.roomDB.AuthException
import com.example.spiice.roomDB.PasswordMismatchException
import com.example.spiice.roomDB.dao.AccountDao
import com.example.spiice.roomDB.entities.UserTuple
import com.example.spiice.utils.securityUtils.SecurityUtils
import com.example.spiice.utils.toAccountDBEntity
import javax.inject.Inject

class AccountRoomDbRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val securityUtils: SecurityUtils
) : AccountRepository {

    override suspend fun getAccount(email: String, password: String): String {
        val account = accountDao.getAccountByEmail(email)
            ?: throw AuthException("The password or email are incorrect!")
        val saltBytes = securityUtils.stringToBytes(account.salt)
        val hashPassword = securityUtils.passwordToHash(password.toCharArray(), saltBytes)
        val hashString = securityUtils.bytesToString(hashPassword)
        if (account.hash != hashString) throw PasswordMismatchException("The password or email are incorrect!")
        return account.email
    }

    override suspend fun getUserName(email: String): UserTuple {
        return accountDao.getUserData(email)
    }

    override suspend fun createAccount(signUpAccountData: SignUpAccountData) {
        try {
            accountDao.createAccount(
                signUpAccountData.toAccountDBEntity(securityUtils)
            )
        } catch (e: SQLiteConstraintException) {
            val appException = AccountAlreadyExistException("Account already exist!")
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun deleteAccountByEmail(email: String) {
        accountDao.deleteAccountByEmail(email)
    }
}
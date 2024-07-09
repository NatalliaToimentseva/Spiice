package com.example.spiice.roomDB.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.spiice.models.accountModel.LoginAccountData
import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.roomDB.AccountAlreadyExistException
import com.example.spiice.roomDB.AuthException
import com.example.spiice.roomDB.DataBaseProvider
import com.example.spiice.roomDB.PasswordMismatchException
import com.example.spiice.roomDB.entities.AccountDbEntity
import com.example.spiice.utils.toLoginAccountData

class AccountRoomDbRepository : AccountRepository {

    override fun getAccount(email: String, password: String): LoginAccountData {
        val account = DataBaseProvider.accountDao?.getAccountByEmail(email)?.toLoginAccountData()
            ?: throw AuthException("The password or email are incorrect!")
        if (account.password != password) throw PasswordMismatchException("The password or email are incorrect!")
        return account
    }

    override fun createAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        try {
            DataBaseProvider.accountDao?.createAccount(
                AccountDbEntity(0, firstName, lastName, email, password)
            )
        } catch (e: SQLiteConstraintException) {
            val appException = AccountAlreadyExistException("Account already exist!")
            appException.initCause(e)
            throw appException
        }
    }
}
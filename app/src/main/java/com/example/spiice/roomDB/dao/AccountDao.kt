package com.example.spiice.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spiice.roomDB.entities.AccountDbEntity
import com.example.spiice.roomDB.entities.AccountLogInTuple

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createAccount(accountDbEntity: AccountDbEntity)

    @Query("SELECT id, email, password FROM Accounts WHERE email = :email")
    fun getAccountByEmail(email: String): AccountLogInTuple?
}
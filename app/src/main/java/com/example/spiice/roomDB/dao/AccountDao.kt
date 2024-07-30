package com.example.spiice.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spiice.roomDB.entities.AccountDbEntity
import com.example.spiice.roomDB.entities.AccountLogInTuple
import com.example.spiice.roomDB.entities.UserTuple

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createAccount(accountDbEntity: AccountDbEntity)

    @Query("SELECT id, email, hash, salt FROM Accounts WHERE email = :email")
    suspend fun getAccountByEmail(email: String): AccountLogInTuple?

    @Query("DELETE FROM Accounts WHERE email = :email")
    suspend fun deleteAccountByEmail(email: String)

    @Query("SELECT first_name, last_name FROM Accounts WHERE email = :email" )
    suspend fun getUserData(email: String): UserTuple
}
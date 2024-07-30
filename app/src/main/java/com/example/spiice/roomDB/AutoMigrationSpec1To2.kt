package com.example.spiice.roomDB

import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spiice.utils.securityUtils.DefaultSecurityUtilsImpl

@RenameColumn(tableName = "Accounts", fromColumnName = "password", toColumnName = "hash")
class AutoMigrationSpec1To2 : AutoMigrationSpec {

    private val securityUtils = DefaultSecurityUtilsImpl()

    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        super.onPostMigrate(db)
        db.query("SELECT * FROM Accounts").use { cursor ->
            val passwordIndex = cursor.getColumnIndex("hash")
            val idIndex = cursor.getColumnIndex("id")
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                val passwordChars = cursor.getString(passwordIndex).toCharArray()
                val salt = securityUtils.generateSalt()
                val hashBytes = securityUtils.passwordToHash(passwordChars, salt)
                db.update(
                    "Accounts",
                    SQLiteDatabase.CONFLICT_NONE,
                    contentValuesOf(
                        "hash" to securityUtils.bytesToString(hashBytes),
                        "salt" to securityUtils.bytesToString(salt)
                    ),
                    "id = ?",
                    arrayOf(id.toString())
                )
            }
        }
    }
}
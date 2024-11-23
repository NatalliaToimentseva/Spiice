package com.example.spiice.roomDB

import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spiice.App
import com.example.spiice.utils.securityUtils.SecurityUtils
import javax.inject.Inject

private const val QUERY_FOR_PASSWORD_MIGRATION = "SELECT * FROM Accounts"
private const val TABLE_ACCOUNTS = "Accounts"
private const val TABLE_OLD_COLUMN = "password"
private const val TABLE_NEW_COLUMN = "hash"
private const val SALT = "salt"
private const val ID = "id"
private const val CLAUSE = "id = ?"

@RenameColumn(
    tableName = TABLE_ACCOUNTS,
    fromColumnName = TABLE_OLD_COLUMN,
    toColumnName = TABLE_NEW_COLUMN
)
class AutoMigrationSpec1To2 : AutoMigrationSpec {

    @Inject
    lateinit var securityUtils: SecurityUtils

    init {
        App.appComponent?.inject(this)
    }

    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        super.onPostMigrate(db)
        db.query(QUERY_FOR_PASSWORD_MIGRATION).use { cursor ->
            val passwordIndex = cursor.getColumnIndex(TABLE_NEW_COLUMN)
            val idIndex = cursor.getColumnIndex(ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                val passwordChars = cursor.getString(passwordIndex).toCharArray()
                val salt = securityUtils.generateSalt()
                val hashBytes = securityUtils.passwordToHash(passwordChars, salt)
                db.update(
                    TABLE_ACCOUNTS,
                    SQLiteDatabase.CONFLICT_NONE,
                    contentValuesOf(
                        TABLE_NEW_COLUMN to securityUtils.bytesToString(hashBytes),
                        SALT to securityUtils.bytesToString(salt)
                    ),
                    CLAUSE,
                    arrayOf(id.toString())
                )
            }
        }
    }
}
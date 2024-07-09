package com.example.spiice.roomDB.dao

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@RenameColumn(tableName = "Accounts", fromColumnName = "password", toColumnName = "hash")
class AutoMigrationSpec1To2 : AutoMigrationSpec
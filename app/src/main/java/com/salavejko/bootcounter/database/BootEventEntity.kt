package com.salavejko.bootcounter.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.salavejko.bootcounter.database.BootEventEntity.Companion.BOOT_COUNTER_TABLE_NAME

@Entity(tableName = BOOT_COUNTER_TABLE_NAME)
data class BootEventEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val date: String
) {
    companion object {
        const val BOOT_COUNTER_TABLE_NAME = "boot_counter"
    }
}
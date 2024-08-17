package com.salavejko.bootcounter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BootEventDao {
    @Query("SELECT * FROM ${BootEventEntity.BOOT_COUNTER_TABLE_NAME}")
    fun getAll(): Flow<List<BootEventEntity>>

    @Insert
    fun insertAll(vararg users: BootEventEntity)
}
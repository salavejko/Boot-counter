package com.salavejko.bootcounter.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BootEventEntity::class], version = 1)
abstract class BootCounterDatabase : RoomDatabase() {
    abstract fun bootEventDao(): BootEventDao
}
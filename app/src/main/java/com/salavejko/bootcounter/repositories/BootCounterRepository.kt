package com.salavejko.bootcounter.repositories

import android.util.Log
import com.salavejko.bootcounter.database.BootCounterDatabase
import com.salavejko.bootcounter.database.BootEventEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface BootCounterRepository {
    suspend fun addBootEvent(bootEvent: BootEventEntity)
    fun getAllBootEvents(): Flow<List<BootEventEntity>>
}


class BootCounterRepositoryImpl @Inject constructor(
    private val bootCounterDatabase: BootCounterDatabase
) : BootCounterRepository {

    override suspend fun addBootEvent(bootEvent: BootEventEntity) {
        withContext(Dispatchers.IO) {
            try {
                bootCounterDatabase.bootEventDao().insertAll(bootEvent)
            } catch (_: Exception) {
                Log.e("BootCounterRepository", "Error adding boot event")
            }
        }
    }

    override fun getAllBootEvents(): Flow<List<BootEventEntity>> {
        return bootCounterDatabase.bootEventDao().getAll()
    }
}
package com.salavejko.bootcounter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.salavejko.bootcounter.database.BootEventEntity
import com.salavejko.bootcounter.repositories.BootCounterRepository
import com.salavejko.bootcounter.utils.goAsync
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject


@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: BootCounterRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            goAsync {
                withContext(Dispatchers.IO) {
                    val currentDateString = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
                    repository.addBootEvent(BootEventEntity(date = currentDateString))
                }
            }
        }
    }
}
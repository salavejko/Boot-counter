package com.salavejko.bootcounter.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.salavejko.bootcounter.repositories.BootCounterRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext

@HiltWorker
class BootCounterActionWorker @AssistedInject constructor(
    private val repo: BootCounterRepository,
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    private suspend fun createNotification(): Notification {

        val allEvents = repo.getAllBootEvents().single()
        // TODO ${time_between_2_last_boot_events}”. “Time_between_2_last_boot_events” must be the delta between last and pre-last boot events.
        val message = when {
            allEvents.isEmpty() -> "No boots detected"
            allEvents.size == 1 -> "The boot was detected = ${allEvents[0].date}”. “date_of_the_boot_event”"
            else -> {
                "Last boots time delta = TODO"
            }
        }
        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("BootCounter Notification")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .build()
    }

    override suspend fun doWork(): Result {
        withContext(Dispatchers.Main) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Notification Channel",
                    NotificationManager.IMPORTANCE_HIGH
                )
                val notificationManager =
                    applicationContext.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }

            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, createNotification())
        }

        return Result.success()
    }

    companion object {
        const val CHANNEL_ID = "notification_channel_id"
        const val NOTIFICATION_ID = 1
    }

}
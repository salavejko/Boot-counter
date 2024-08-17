package com.salavejko.bootcounter

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.salavejko.bootcounter.worker.BootCounterActionWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
open class BootCounterApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        //TODO add repeat interval logic
        val saveRequest =
            PeriodicWorkRequestBuilder<BootCounterActionWorker>(15, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("Counter", ExistingPeriodicWorkPolicy.UPDATE, saveRequest)

    }

}

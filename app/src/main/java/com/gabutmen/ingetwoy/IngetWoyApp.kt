package com.gabutmen.ingetwoy

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.gabutmen.ingetwoy.worker.ReminderWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class IngetWoyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val reminderWorkRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<ReminderWorker>(
            24, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "reminder_check_work",
            ExistingPeriodicWorkPolicy.KEEP,
            reminderWorkRequest
        )
    }
}
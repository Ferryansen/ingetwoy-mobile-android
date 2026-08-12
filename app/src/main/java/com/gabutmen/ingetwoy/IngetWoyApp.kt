package com.gabutmen.ingetwoy

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkRequest
import com.gabutmen.ingetwoy.worker.ReminderWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class IngetWoyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val testWorkRequest: WorkRequest = PeriodicWorkRequestBuilder<ReminderWorker>(
            24, TimeUnit.HOURS
        ).build()
    }
}
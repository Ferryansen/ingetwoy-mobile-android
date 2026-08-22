package com.gabutmen.ingetwoy

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.gabutmen.ingetwoy.worker.DDayReminderWorker
import com.gabutmen.ingetwoy.worker.OffsetReminderWorker
import dagger.hilt.android.HiltAndroidApp
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class IngetWoyApp: Application(), Configuration.Provider {
    @Inject lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration get() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        registerReminderWorkers()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channelId = "reminder_channel"
        val name = "Reminder Notif"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val description = "Channel to set IngetWoy's reminder notif"

        val mChannel = NotificationChannel(channelId, name, importance)
        mChannel.description = description
        val notificationManager = this.getSystemService(NotificationManager::class.java)
        requireNotNull(notificationManager){"Null Notification Manager"}.createNotificationChannel(mChannel)
    }

    private fun registerReminderWorkers() {
        val dDayReminderWorkRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<DDayReminderWorker>(
                24, TimeUnit.HOURS
            ).setInitialDelay(calculateDelay(10, 0))
                .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "d_day_reminder_work",
            ExistingPeriodicWorkPolicy.KEEP,
            dDayReminderWorkRequest
        )

        val offsetReminderWorkRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<OffsetReminderWorker>(
                24, TimeUnit.HOURS
            ).setInitialDelay(calculateDelay(12, 0))
                .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "offset_reminder_work",
            ExistingPeriodicWorkPolicy.KEEP,
            offsetReminderWorkRequest
        )
    }

    private fun calculateDelay(hour: Int, minute: Int): Duration {
        val currentTime = LocalDateTime.now()
        var targetTime = LocalDate.now().atTime(hour, minute)

        if (targetTime.isBefore(currentTime)) {
            targetTime = targetTime.plusDays(1)
        }

        return Duration.between(currentTime, targetTime)
    }
}


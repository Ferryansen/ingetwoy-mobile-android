package com.gabutmen.ingetwoy.notification

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationPermissionChecker @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun hasPermission(): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

}
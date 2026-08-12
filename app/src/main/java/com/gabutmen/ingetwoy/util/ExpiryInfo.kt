package com.gabutmen.ingetwoy.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit

enum class ExpiryStatus {
    EXPIRED,
    EXPIRING_TODAY,
    SAFE
}

fun daysUntilExpiry(expirationDate: LocalDate): Long {
    return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate)
}

fun expiryStatus(daysRemain: Long): ExpiryStatus {
    if(daysRemain > 0) {
        return ExpiryStatus.SAFE
    } else if(daysRemain == 0L) {
        return ExpiryStatus.EXPIRING_TODAY
    }

    return ExpiryStatus.EXPIRED
}
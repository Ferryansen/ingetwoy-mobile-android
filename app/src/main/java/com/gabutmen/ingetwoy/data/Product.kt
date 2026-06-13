package com.gabutmen.ingetwoy.data

import java.time.LocalDate

data class Product(
    val id: Long = 0L,
    val name: String,
    val category: String,
    val purchaseDate: LocalDate,
    val expirationDate: LocalDate,
    val notes: String?,
    var extraReminders: List<ReminderOffset> = emptyList()
)
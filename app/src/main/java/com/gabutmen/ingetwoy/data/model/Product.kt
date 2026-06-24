package com.gabutmen.ingetwoy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val category: String,
    val purchaseDate: LocalDate,
    val expirationDate: LocalDate,
    val notes: String?,
    val extraReminders: List<ReminderOffset> = emptyList()
)
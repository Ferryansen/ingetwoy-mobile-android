package com.gabutmen.ingetwoy.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val category: String,
    @ColumnInfo(name = "purchase_date")
    val purchaseDate: LocalDate,
    @ColumnInfo(name = "expiration_date")
    val expirationDate: LocalDate,
    val notes: String?,
    val reminders: List<ReminderOffset> = emptyList()
)
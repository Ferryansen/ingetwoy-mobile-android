package com.gabutmen.ingetwoy.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.LocalDate

@Entity(
    tableName = "remindidemlogs",
    primaryKeys = ["product_id", "reminder_type", "created_at"],
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ReminderIdempotencyLog(
    @ColumnInfo(name = "product_id")
    val productId: Long,
    @ColumnInfo(name = "reminder_type")
    val reminderType: String,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDate
)

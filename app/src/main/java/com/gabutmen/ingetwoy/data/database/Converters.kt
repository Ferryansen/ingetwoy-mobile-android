package com.gabutmen.ingetwoy.data.database

import androidx.room.TypeConverter
import com.gabutmen.ingetwoy.data.model.ReminderOffset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate


class Converters {
    companion object {
        private val gson = Gson()

    }

//    Reminder list converter
    @TypeConverter
    fun fromList(reminderList: List<ReminderOffset>): String {
        return gson.toJson(reminderList)
    }

    @TypeConverter
    fun toList(reminderJson: String): List<ReminderOffset> {
        return gson.fromJson(reminderJson, object: TypeToken<List<ReminderOffset>>() {}.type)
    }

//    Date converter
    @TypeConverter
    fun fromLocalDate(date: LocalDate): Long {
        return date.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(digitedDate: Long): LocalDate {
        return LocalDate.ofEpochDay(digitedDate)
    }
}
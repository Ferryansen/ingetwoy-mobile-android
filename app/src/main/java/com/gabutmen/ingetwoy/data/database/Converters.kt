package com.gabutmen.ingetwoy.data.database

import androidx.room.TypeConverter
import com.gabutmen.ingetwoy.data.model.ReminderOffset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    companion object {
        val gson = Gson()

    }

    @TypeConverter
    fun fromList(reminderList: List<ReminderOffset>): String {
        return gson.toJson(reminderList)
    }

    @TypeConverter
    fun toList(reminderJson: String): List<ReminderOffset> {
        return gson.fromJson(reminderJson, object: TypeToken<List<ReminderOffset>>() {}.type)
    }
}
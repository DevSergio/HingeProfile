package com.hinge.profile.app.persistence.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HobbiesConverter {

    var gson = Gson()

    @TypeConverter
    fun fromHobbies(hobbies: List<String>?): String {
        return gson.toJson(hobbies)
    }

    @TypeConverter
    fun toHobbies(data: String): List<String>? {
        val listType = object : TypeToken<List<String>?>() {
        }.type
        return gson.fromJson(data, listType)
    }
}
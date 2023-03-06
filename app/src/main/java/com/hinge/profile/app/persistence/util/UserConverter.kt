package com.hinge.profile.app.persistence.util

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.hinge.profile.app.domain.model.User

object UserConverter {
    @TypeConverter
    fun fromUser(user: User): String {
        return HobbiesConverter.gson.toJson(user)

    }

    @TypeConverter
    fun toUser(data: String): User {
        val listType = object : TypeToken<User>() {
        }.type
        return HobbiesConverter.gson.fromJson(data, listType)
    }
}
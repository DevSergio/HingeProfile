package com.hinge.profile.app.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val photo: String? = null,
    val gender: String,
    val school: String? = null,
    val about: String? = null,
    val hobbies: List<String>? = null,
)
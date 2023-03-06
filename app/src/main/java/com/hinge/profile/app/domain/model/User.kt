package com.hinge.profile.app.domain.model

data class User(
    val id: Int,
    val name: String,
    val photo: String? = null,
    val gender: String,
    val school: String? = null,
    val about: String? = null,
    val hobbies: List<String>? = null,
)
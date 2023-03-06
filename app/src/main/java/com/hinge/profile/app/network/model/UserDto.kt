package com.hinge.profile.app.network.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("about")
    var about: String? = null,
    @SerializedName("school")
    var school: String? = null,
    @SerializedName("hobbies")
    var hobbies: List<String>? = null,
)

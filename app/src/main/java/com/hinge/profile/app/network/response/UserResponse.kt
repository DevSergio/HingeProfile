package com.hinge.profile.app.network.response

import com.google.gson.annotations.SerializedName
import com.hinge.profile.app.network.model.UserDto

data class UserResponse(
    @SerializedName("users")
    var users: List<UserDto>,
)

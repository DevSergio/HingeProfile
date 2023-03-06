package com.hinge.profile.app.network.response

import com.google.gson.annotations.SerializedName

data class ConfigResponse(
    @SerializedName("profile")
    var profile: List<String>,
)

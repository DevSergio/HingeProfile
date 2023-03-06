package com.hinge.profile.app.network

import com.hinge.profile.app.network.response.ConfigResponse
import com.hinge.profile.app.network.response.UserResponse
import retrofit2.http.GET

interface ProfileService {

    @GET("users")
    suspend fun getUsers(): UserResponse

    @GET("config")
    suspend fun config(): ConfigResponse

}
package com.app.coroutines_kotlin.remote.response

import com.app.coroutines_kotlin.model.User
import com.squareup.moshi.Json


data class UserResponse(
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "login") val username: String
)

fun UserResponse.toModel() = User(this.avatarUrl, this.username)
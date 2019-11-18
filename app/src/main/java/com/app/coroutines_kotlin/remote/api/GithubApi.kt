package com.app.coroutines_kotlin.remote.api

import com.app.coroutines_kotlin.remote.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/*api calling class*/

interface GithubApi {

    @GET("users")
    fun getAll(): Deferred<List<UserResponse>>

    @GET("users/{username}")
    fun getByUsername(@Path("username") username: String): Deferred<UserResponse>

}
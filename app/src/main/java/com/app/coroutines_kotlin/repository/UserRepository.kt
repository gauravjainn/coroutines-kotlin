package com.app.coroutines_kotlin.repository

import com.app.coroutines_kotlin.model.User


interface UserRepository {

    suspend fun getAll(): List<User>

    suspend fun getByUsername(username: String): User

}
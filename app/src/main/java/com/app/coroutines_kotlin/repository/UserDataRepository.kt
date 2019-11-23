package com.app.coroutines_kotlin.repository

import com.app.coroutines_kotlin.remote.api.GithubApi
import com.app.coroutines_kotlin.remote.response.toModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext


class UserDataRepository(
        private val api: GithubApi
) : UserRepository {

    override suspend fun getAll() = withContext(IO) {
        api.getAll().await().map { it.toModel() }
    }

    override suspend fun getByUsername(username: String) = withContext(IO) {
        api.getByUsername(username).await().toModel()


    }

}
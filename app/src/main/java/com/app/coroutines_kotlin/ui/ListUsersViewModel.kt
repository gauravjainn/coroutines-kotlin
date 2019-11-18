package com.app.coroutines_kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coroutines_kotlin.model.User
import com.app.coroutines_kotlin.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ListUsersViewModel(
    private val repository: UserRepository) : ViewModel() {

/*    SupervisorJob -  A failure of a child does not cause this scope to fail and does not affect its other children,
     so a custom policy for handling failures of its children can be implemented.*/

    private val viewModelJob = SupervisorJob()

/*    CoroutineScope -  to check current scope is active or not */

private val viewModeScope = CoroutineScope(Main + viewModelJob)

private val _users: MutableLiveData<List<User>> = MutableLiveData()

private val _loading: MutableLiveData<Boolean> = MutableLiveData()

private val _error: MutableLiveData<Throwable> = MutableLiveData()

val users: LiveData<List<User>> get() = _users

val loading: LiveData<Boolean> get() = _loading

val error: LiveData<Throwable> get() = _error

fun getAll() {
    viewModeScope.launch {
        _loading.value = true
        try {
            _users.value = repository.getAll()
            _loading.value = false
        } catch(t: Throwable) {
            _users.value = emptyList()
            _error.value = t
        } finally {
            _loading.value = false
        }
    }
}

fun getByUsername(username: String) {
    viewModeScope.launch {
        _loading.value = true
        try {
            val user = repository.getByUsername(username)
            _users.value = listOf(user)
        } catch(t: Throwable) {
            _users.value = emptyList()
            _error.value = t
        }
        finally {
            _loading.value = false
        }
    }
}

override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
}

}
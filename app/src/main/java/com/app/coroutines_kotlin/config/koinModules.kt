package com.app.coroutines_kotlin.config

import com.app.coroutines_kotlin.remote.api.GithubApi
import com.app.coroutines_kotlin.repository.UserDataRepository
import com.app.coroutines_kotlin.repository.UserRepository
import com.app.coroutines_kotlin.ui.ListUsersViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val uiModule = module {
    viewModel { ListUsersViewModel(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserDataRepository(get()) }
}

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<GithubApi>(get()) }
}

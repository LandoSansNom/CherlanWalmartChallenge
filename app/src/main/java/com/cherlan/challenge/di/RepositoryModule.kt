package com.cherlan.challenge.di

import com.cherlan.challenge.data.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.koin.dsl.module

val repositoryModule = module {

    single { RepositoryImpl(get()) }
}
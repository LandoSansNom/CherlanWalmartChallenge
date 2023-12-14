package com.cherlan.challenge.di

import com.cherlan.challenge.data.repository.RepositoryImpl
import com.cherlan.challenge.domain.usecase.GetAllCountriesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAllCountriesUseCase(get()) }
}
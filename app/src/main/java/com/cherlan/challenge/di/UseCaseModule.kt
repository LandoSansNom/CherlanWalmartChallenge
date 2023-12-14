package com.cherlan.challenge.di

import com.cherlan.challenge.domain.usecase.GetAllCountriesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAllCountriesUseCase(repository =  get()) }
}
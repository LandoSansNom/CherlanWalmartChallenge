package com.cherlan.challenge.di

import com.cherlan.challenge.data.repository.Repository
import com.cherlan.challenge.data.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<Repository>  { RepositoryImpl(get()) }
}
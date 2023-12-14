package com.cherlan.challenge.ui

import android.app.Application
import com.cherlan.challenge.di.networkModule
import com.cherlan.challenge.di.repositoryModule
import com.cherlan.challenge.di.useCaseModule
import com.cherlan.challenge.di.viewModelModule
import org.koin.core.context.startKoin

class CountryApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(useCaseModule)
        }
    }
}
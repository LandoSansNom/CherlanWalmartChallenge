package com.cherlan.challenge.di

import com.cherlan.challenge.ui.countryScreen.CountryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryViewModel(get()) }
}
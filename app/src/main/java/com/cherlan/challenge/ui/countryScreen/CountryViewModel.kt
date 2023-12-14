package com.cherlan.challenge.ui.countryScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherlan.challenge.domain.Result
import com.cherlan.challenge.domain.usecase.GetAllCountriesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountryViewModel constructor(
    private var getAllCountriesUseCase: GetAllCountriesUseCase
) : ViewModel() {

    private val _countryList: MutableLiveData<Result> = MutableLiveData()
    val countryList: LiveData<Result> get() = _countryList


    init {
        getAllCountries()
    }

    fun getAllCountries(){
        viewModelScope.launch {
            getAllCountriesUseCase.getAllCountries().collect {
                _countryList.postValue(it)
            }
        }
    }
}
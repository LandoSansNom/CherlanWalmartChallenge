package com.cherlan.challenge.data.remote

class ApiCall(private val apiService: ApiService) {
    suspend fun getCountries() = apiService.getCountries()
}
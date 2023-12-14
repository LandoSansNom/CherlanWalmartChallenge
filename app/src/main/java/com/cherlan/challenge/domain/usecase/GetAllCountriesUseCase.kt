package com.cherlan.challenge.domain.usecase

import com.cherlan.challenge.data.repository.Repository
import com.cherlan.challenge.data.repository.RepositoryImpl
import kotlinx.coroutines.flow.Flow
import com.cherlan.challenge.domain.Result


class GetAllCountriesUseCase constructor(
    private val repository: Repository
) {
     fun getAllCountries(): Flow<Result> {
         return repository.getAllCountries()
     }

}
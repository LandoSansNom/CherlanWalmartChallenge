package com.cherlan.challenge.domain.usecase

import com.cherlan.challenge.data.repository.RepositoryImpl
import kotlinx.coroutines.flow.Flow
import com.cherlan.challenge.domain.Result


class GetAllCountriesUseCase constructor(
    private val repositoryImpl: RepositoryImpl
) {
     fun getAllCountries(): Flow<Result> {
         return repositoryImpl.getAllCountries()
     }

}
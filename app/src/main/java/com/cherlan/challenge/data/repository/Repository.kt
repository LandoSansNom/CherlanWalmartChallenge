package com.cherlan.challenge.data.repository

import com.cherlan.challenge.domain.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
     fun getAllCountries(): Flow<Result>
}
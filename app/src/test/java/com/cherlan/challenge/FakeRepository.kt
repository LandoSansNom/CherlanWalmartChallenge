package com.cherlan.challenge

import androidx.lifecycle.LiveData
import com.cherlan.challenge.data.model.Country
import com.cherlan.challenge.data.repository.Repository
import com.cherlan.challenge.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRepository : Repository{
    val flow = MutableStateFlow<Result>(Result.Loading)
    suspend fun emit(value: Result) = flow.emit(value)
    override fun getAllCountries(): Flow<Result> = flow


}
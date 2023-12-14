package com.cherlan.challenge.data.repository

import com.cherlan.challenge.data.remote.ApiCall
import com.cherlan.challenge.domain.Result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


class RepositoryImpl constructor(
    private val apiCall: ApiCall,
): Repository {
    override fun getAllCountries(): Flow<Result> {
        return flow {
            emit(Result.Loading)
            val response = apiCall.getCountries()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Failure(response.message()))
            } else {
                emit(Result.Failure(response.message()))
            }
        }.catch {
            emit(Result.Failure(it.message.toString()))
        }
    }
}
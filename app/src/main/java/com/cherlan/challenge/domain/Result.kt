package com.cherlan.challenge.domain

import com.cherlan.challenge.data.model.Country

sealed class Result {
    data class Success(val data: List<Country>): Result()
    data class Failure(val error: String): Result()
    object Loading: Result()
}

package com.cherlan.challenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cherlan.challenge.data.model.Country
import com.cherlan.challenge.domain.Result
import com.cherlan.challenge.domain.usecase.GetAllCountriesUseCase
import com.cherlan.challenge.ui.countryScreen.CountryViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations


class CountryViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var countryViewModel: CountryViewModel

    @Mock
    lateinit var getAllCountriesUseCase: GetAllCountriesUseCase

    val testScheduler = TestCoroutineScheduler()
    val testDispatcher = StandardTestDispatcher(testScheduler)
    val testScope = TestScope(testDispatcher)



    @Before
    fun startUp() {
        MockitoAnnotations.openMocks(this)
        countryViewModel = CountryViewModel(getAllCountriesUseCase)

    }

    @Test
    fun `fetch three countries and succeed`() = testScope.runTest {
        val countries = flowOf(
            Result.Success(
                listOf(
                    Country(name = "France", capital = "Paris"),
                    Country(name = "USA", capital = "Washington DC"),
                    Country(name = "England", capital = "Londres")
                )
            )
        )

        Mockito.`when`(getAllCountriesUseCase.getAllCountries()).thenReturn ( countries )

        countryViewModel.getAllCountries() // trigger the usecase
        advanceUntilIdle()
        assertThat(countryViewModel.countryList.value).isEqualTo(countries)

    }

    @Test
    fun `fetch countries and succeed with empty list`() = testScope.runTest {
        val result = flowOf(Result.Loading)

        Mockito.`when`(getAllCountriesUseCase.getAllCountries()).then { result }

        countryViewModel.getAllCountries() // trigger the usecase
        advanceUntilIdle()

        assertThat(countryViewModel.countryList.value).isEqualTo(result)

    }

    @Test
    fun `fetch countries and fail`() = testScope.runTest {
        val result = flowOf(Result.Failure("Failed because of test usecase"))
        Mockito.`when`(getAllCountriesUseCase.getAllCountries()).then { result }

        countryViewModel.getAllCountries() // trigger the usecase
        advanceUntilIdle()

        assertThat(countryViewModel.countryList.value).isEqualTo(result)

    }


}

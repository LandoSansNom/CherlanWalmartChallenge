package com.cherlan.challenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cherlan.challenge.data.model.Country
import com.cherlan.challenge.domain.Result
import com.cherlan.challenge.domain.usecase.GetAllCountriesUseCase
import com.cherlan.challenge.ui.countryScreen.CountryViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    lateinit var fakeRepository: FakeRepository
    lateinit var getAllCountriesUseCase: GetAllCountriesUseCase




    @Before
    fun startUp() {
        MockitoAnnotations.openMocks(this)
        fakeRepository = FakeRepository()
        getAllCountriesUseCase = GetAllCountriesUseCase(fakeRepository)
        countryViewModel = CountryViewModel(getAllCountriesUseCase)

    }

    @Test
    fun `fetch countries and succeed with empty list`() = runTest {

        countryViewModel.getAllCountries() // trigger the usecase
        advanceUntilIdle()
        assertThat(countryViewModel.countryList.value).isEqualTo(Result.Loading)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch three countries and succeed`() = runTest {


        val countries =
            Result.Success(
                listOf(
                    Country(name = "France", capital = "Paris"),
                    Country(name = "USA", capital = "Washington DC"),
                    Country(name = "England", capital = "Londres")
                )
            )


        fakeRepository.emit(countries)
        countryViewModel.getAllCountries() // trigger the usecase
        advanceUntilIdle()
        assertThat(countryViewModel.countryList.value).isEqualTo(countries)

    }

    @Test
    fun `fetch countries and fail`() = runTest {
        val result = Result.Failure("Failed because of test usecase")

        fakeRepository.emit(result)
        countryViewModel.getAllCountries() // trigger the usecase
        advanceUntilIdle()
        assertThat(countryViewModel.countryList.value).isEqualTo(result)

    }


}

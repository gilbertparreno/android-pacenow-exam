package com.pacenow.exam.settings.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.pacenow.exam.core.network.enums.TaskStatus
import com.pacenow.exam.core.network.repositories.AppRepository
import com.pacenow.exam.core.room.repositories.CountryRepository
import com.pacenow.exam.ui.settings.viewModels.SettingsViewModel
import com.pacenow.exam.utils.TestCoroutineRule
import com.pacenow.exam.utils.providers.TestCoroutineContextProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SettingsViewModelTest {

    @get:Rule val instantTestExecutorRule = InstantTaskExecutorRule()
    @get:Rule val testCoroutineRule = TestCoroutineRule()

    @MockK
    private lateinit var appRepository: AppRepository
    @MockK
    private lateinit var countryRepository: CountryRepository

    private lateinit var viewModel: SettingsViewModel
    private lateinit var testCoroutineContextProvider: TestCoroutineContextProvider
    private lateinit var testCountriesEvent: TestObserver<TaskStatus<Any>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testCoroutineContextProvider = TestCoroutineContextProvider(testCoroutineRule)
        viewModel = SettingsViewModel(
            appRepository = appRepository,
            coroutineContextProvider = testCoroutineContextProvider,
            countryRepository = countryRepository
        ).apply {
            testCountriesEvent = countriesEvent.test()
        }
    }

    @Test
    fun `getCountries() - SUCCESS`() = testCoroutineRule.runBlockingTest {
        coEvery { countryRepository.count() } returns 0
        coEvery { countryRepository.insert() } returns Unit
        coEvery { appRepository.getCountries() } returns listOf()

        viewModel.getCountries()
        advanceTimeBy(300)

        coVerifyOrder {
            countryRepository.count()
            appRepository.getCountries()
            countryRepository.insert(*arrayOf())
        }
        testCountriesEvent.assertValueHistory(
            TaskStatus.loading(true),
            TaskStatus.success()
        )
    }

    @Test
    fun `getCountries() - FAILURE`() = testCoroutineRule.runBlockingTest {
        val error = Error("This is a test exception")
        coEvery { countryRepository.count() } returns 0
        coEvery { appRepository.getCountries() } throws error

        viewModel.getCountries()
        advanceTimeBy(300)

        coVerify {
            countryRepository.count()
            appRepository.getCountries()
        }
        testCountriesEvent.assertValueHistory(
            TaskStatus.loading(true),
            TaskStatus.error(error)
        )
    }
}
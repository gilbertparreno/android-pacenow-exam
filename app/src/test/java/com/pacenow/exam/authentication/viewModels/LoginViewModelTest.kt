package com.pacenow.exam.authentication.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.pacenow.exam.core.network.enums.TaskStatus
import com.pacenow.exam.core.room.repositories.CountryRepository
import com.pacenow.exam.core.room.repositories.UserRepository
import com.pacenow.exam.core.sharedPreferences.UserSharedPreferences
import com.pacenow.exam.ui.authentication.managers.AuthenticationManager
import com.pacenow.exam.ui.authentication.viewModels.LoginViewModel
import com.pacenow.exam.utils.TestCoroutineRule
import com.pacenow.exam.utils.providers.TestCoroutineContextProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule val instantTestExecutorRule = InstantTaskExecutorRule()
    @get:Rule val testCoroutineRule = TestCoroutineRule()

    @MockK(relaxUnitFun = true)
    private lateinit var authenticationManager: AuthenticationManager
    @MockK
    private lateinit var userRepository: UserRepository
    @MockK
    private lateinit var userSharedPreferences: UserSharedPreferences
    @MockK
    private lateinit var countryRepository: CountryRepository

    private lateinit var viewModel: LoginViewModel
    private lateinit var testCoroutineContextProvider: TestCoroutineContextProvider
    private lateinit var testAuthenticateEvent: TestObserver<TaskStatus<Any>>

    private val testEmailAddress = "test@gmail.com"
    private val testPassword = "p@ssword".toByteArray()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testCoroutineContextProvider = TestCoroutineContextProvider(testCoroutineRule)
        viewModel = LoginViewModel(
            userRepository = userRepository,
            authenticationManager = authenticationManager,
            coroutineContextProvider = testCoroutineContextProvider,
            userSharedPreferences = userSharedPreferences,
            countryRepository = countryRepository
        ).apply {
            testAuthenticateEvent = authenticateEvent.test()
        }
    }

    @Test
    fun `authenticate() - SUCCESS`() = testCoroutineRule.runBlockingTest {
        coEvery {
            authenticationManager.authenticateUser(
                testEmailAddress,
                testPassword,
                true
            )
        } returns Unit
        viewModel.authenticate(testEmailAddress, testPassword, true)
        coVerify {
            authenticationManager.authenticateUser(testEmailAddress, testPassword, true)
        }
        testAuthenticateEvent.assertValue { it != null }
    }

    @Test
    fun `authenticate() - FAILURE`() = testCoroutineRule.runBlockingTest {
        val error = Error("This is a test exception")
        coEvery {
            authenticationManager.authenticateUser(
                testEmailAddress,
                testPassword,
                true
            )
        } throws error
        viewModel.authenticate(testEmailAddress, testPassword, true)
        coVerify {
            authenticationManager.authenticateUser(testEmailAddress, testPassword, true)
        }
        testAuthenticateEvent.assertValue(TaskStatus.error(error))
    }
}
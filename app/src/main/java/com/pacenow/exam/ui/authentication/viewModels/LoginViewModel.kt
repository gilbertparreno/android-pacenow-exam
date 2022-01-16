package com.pacenow.exam.ui.authentication.viewModels

import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacenow.exam.BuildConfig
import com.pacenow.exam.core.extensions.SingleLiveEvent
import com.pacenow.exam.core.extensions.launch
import com.pacenow.exam.core.extensions.logError
import com.pacenow.exam.core.extensions.logInfo
import com.pacenow.exam.core.network.enums.TaskStatus
import com.pacenow.exam.core.providers.CoroutineContextProvider
import com.pacenow.exam.core.room.entities.User
import com.pacenow.exam.core.room.repositories.CountryRepository
import com.pacenow.exam.core.room.repositories.UserRepository
import com.pacenow.exam.core.security.Encryption
import com.pacenow.exam.core.sharedPreferences.UserSharedPreferences
import com.pacenow.exam.ui.authentication.managers.AuthenticationManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val userSharedPreferences: UserSharedPreferences,
    private val countryRepository: CountryRepository
) : ViewModel() {

    val authenticateEvent = SingleLiveEvent<TaskStatus<Any>>()

    init {
        addTestUsersIfNeeded()
    }

    fun authenticate(
        email: String,
        password: ByteArray,
        rememberMe: Boolean
    ) {
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                authenticationManager.authenticateUser(
                    email = email,
                    password = password,
                    rememberMe = rememberMe
                )
            },
            onSuccess = {
                authenticateEvent.value = TaskStatus.success()
            },
            onFailure = {
                authenticateEvent.value = TaskStatus.error(it)
            }
        )
    }

    fun clear() {
        viewModelScope.launch {
            userSharedPreferences.clear()
            countryRepository.deleteAllRows()
        }
    }

    @DelicateCoroutinesApi
    private fun addTestUsersIfNeeded() {
        GlobalScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                if (userRepository.findAll().isEmpty()) {
                    val users = listOf(
                        Pair("user1@gmail.com", "user1P@ssword"),
                        Pair("user2@gmail.com", "user2P@ssword"),
                        Pair("user3@gmail.com", "user3P@ssword")
                    ).mapIndexedNotNull { index, item ->
                        Encryption.encrypt(
                            item.second.toByteArray(Charsets.UTF_8),
                            BuildConfig.MASTER_PASSWORD.toCharArray()
                        )?.let { map ->
                            User(
                                userName = item.first,
                                passwordSalt = Base64.encodeToString(map["salt"], Base64.NO_WRAP),
                                passwordIv = Base64.encodeToString(map["iv"], Base64.NO_WRAP),
                                passwordEncrypted = Base64.encodeToString(
                                    map["encrypted"],
                                    Base64.NO_WRAP
                                ),
                                firstName = "User_%s".format(index),
                                lastName = "User_%s".format(index)
                            )
                        }
                    }

                    userRepository.insert(*users.toTypedArray())
                }
            }, onSuccess = {
                logInfo("Test users created")
            }, onFailure = {
                logError(it)
            }
        )
    }
}
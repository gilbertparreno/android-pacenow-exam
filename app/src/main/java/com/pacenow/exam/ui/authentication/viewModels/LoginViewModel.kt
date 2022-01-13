package com.pacenow.exam.ui.authentication.viewModels

import androidx.lifecycle.ViewModel
import com.pacenow.exam.core.room.repositories.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel()
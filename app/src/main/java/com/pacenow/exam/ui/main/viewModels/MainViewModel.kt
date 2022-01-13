package com.pacenow.exam.ui.main.viewModels

import androidx.lifecycle.ViewModel
import com.pacenow.exam.core.room.repositories.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel()
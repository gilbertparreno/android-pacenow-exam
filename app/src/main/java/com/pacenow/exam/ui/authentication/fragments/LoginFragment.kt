package com.pacenow.exam.ui.authentication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentLifeCycle
import com.pacenow.exam.core.extensions.showErrorSnackbar
import com.pacenow.exam.core.network.enums.TaskStatus.*
import com.pacenow.exam.core.sharedPreferences.UserSharedPreferences
import com.pacenow.exam.ui.authentication.viewModels.LoginViewModel
import com.pacenow.exam.ui.authentication.views.LoginView
import com.pacenow.exam.ui.authentication.views.LoginViewDelegate
import com.pacenow.exam.ui.main.activities.MainActivity
import javax.inject.Inject

class LoginFragment : BaseFragmentLifeCycle<LoginViewModel, LoginView>(), LoginViewDelegate {

    @Inject lateinit var userSharedPreferences: UserSharedPreferences

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = LoginView(context).also {
        it.delegate = this
    }

    override fun onViewCreated(
        contentView: LoginView,
        savedInstanceState: Bundle?
    ) {
        if (userSharedPreferences.isLogged()) {
            intentToMainActivity()
        } else {
            viewModel.clear()
        }
    }

    override fun observerChanges() {
        viewModel.authenticateEvent.observe(this) {
            when (it) {
                is Success -> intentToMainActivity()
                is Failure -> contentView.showErrorSnackbar(getString(R.string.login_failed_error))
            }
        }
    }

    // LoginViewDelegate

    override fun onLoginPressed(
        email: String,
        password: ByteArray,
        rememberMe: Boolean
    ) {
        viewModel.authenticate(
            email = email,
            password = password,
            rememberMe = rememberMe
        )
    }

    private fun intentToMainActivity() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }
}
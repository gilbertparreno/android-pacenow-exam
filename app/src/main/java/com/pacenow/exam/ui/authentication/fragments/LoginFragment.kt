package com.pacenow.exam.ui.authentication.fragments

import android.content.Context
import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.core.base.BaseFragmentLifeCycle
import com.pacenow.exam.ui.authentication.viewModels.LoginViewModel
import com.pacenow.exam.ui.authentication.views.LoginView

class LoginFragment : BaseFragmentLifeCycle<LoginViewModel, LoginView>() {

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = LoginView(context)

    override fun onViewCreated(contentView: LoginView, savedInstanceState: Bundle?) {}

    override fun observerChanges() {}
}
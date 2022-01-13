package com.pacenow.exam.ui.authentication.views

import android.content.Context
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentView
import com.pacenow.exam.core.extensions.getString
import kotlinx.android.synthetic.main.view_app_bar.view.*

class LoginView(context: Context) : BaseFragmentView(context) {

    init {
        inflate(context, R.layout.view_login, this)
        toolbar.title = getString(R.string.login)
    }
}
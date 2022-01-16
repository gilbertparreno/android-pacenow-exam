package com.pacenow.exam.ui.authentication.views

import android.content.Context
import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentView
import com.pacenow.exam.core.extensions.getString
import com.pacenow.exam.core.extensions.setDebounceClickListener
import com.pacenow.exam.core.extensions.showErrorSnackbar
import kotlinx.android.synthetic.main.view_app_bar.view.*
import kotlinx.android.synthetic.main.view_login.view.*

interface LoginViewDelegate {
    fun onLoginPressed(
        email: String,
        password: ByteArray,
        rememberMe: Boolean
    )
}

class LoginView(context: Context) : BaseFragmentView(context) {

    var delegate: LoginViewDelegate? = null

    init {
        inflate(context, R.layout.view_login, this)
        toolbar.title = getString(R.string.login)

        listOf(email, password).forEach {
            it.addTextChangedListener {
                login.isEnabled = checkIfValidCredentials()
            }
        }

        login.setDebounceClickListener {
            delegate?.onLoginPressed(
                email = email.text.toString(),
                password = password.text.toString().toByteArray(),
                rememberMe = rememberLogin.isChecked
            )
        }
    }

    private fun checkIfValidCredentials(): Boolean {
        if (email.text.isEmpty() || password.text.isEmpty()) {
            return false
        }

        if (password.text.count() < 8) {
            showErrorSnackbar(getString(R.string.login_password_error))
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            showErrorSnackbar(getString(R.string.login_email_error))
            return false
        }

        return true
    }
}
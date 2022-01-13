package com.pacenow.exam.ui.authentication.activities

import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseActivity
import com.pacenow.exam.core.extensions.addFragment
import com.pacenow.exam.core.extensions.getFragmentTag
import com.pacenow.exam.ui.authentication.fragments.LoginFragment

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.apply {
                beginTransaction()
                    .addFragment(
                        containerId = R.id.authenticationContainer,
                        fragmentClass = LoginFragment::class.java,
                        addToBackStack = false
                    ).commit()
            }
            rootFragmentTag = getFragmentTag(LoginFragment::class.java)
        }
    }
}
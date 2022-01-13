package com.pacenow.exam.ui.main.activities

import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseActivity
import com.pacenow.exam.core.extensions.addFragment
import com.pacenow.exam.core.extensions.getFragmentTag
import com.pacenow.exam.ui.main.fragments.MainFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.apply {
                beginTransaction()
                    .addFragment(
                        containerId = R.id.mainContainer,
                        fragmentClass = MainFragment::class.java,
                        addToBackStack = false
                    ).commit()
            }
            rootFragmentTag = getFragmentTag(MainFragment::class.java)
        }
    }
}
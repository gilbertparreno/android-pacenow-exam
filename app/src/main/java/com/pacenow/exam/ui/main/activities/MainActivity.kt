package com.pacenow.exam.ui.main.activities

import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseActivity
import com.pacenow.exam.core.extensions.addFragment
import com.pacenow.exam.core.extensions.getFragmentTag
import com.pacenow.exam.ui.main.fragments.MainPagerFragment

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
                        fragmentClass = MainPagerFragment::class.java,
                        addToBackStack = false
                    ).commit()
            }
            rootFragmentTag = getFragmentTag(MainPagerFragment::class.java)
        }
    }
}
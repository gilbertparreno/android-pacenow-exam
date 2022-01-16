package com.pacenow.exam.ui.main.fragments

import android.content.Context
import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.core.base.BaseFragment
import com.pacenow.exam.ui.main.views.MainPagerView

class MainPagerFragment : BaseFragment<MainPagerView>() {

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = MainPagerView(context)

    override fun onViewCreated(
        contentView: MainPagerView,
        savedInstanceState: Bundle?
    ) {
        contentView.setUpView(this)
    }
}
package com.pacenow.exam.ui.main.fragments

import android.content.Context
import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.core.base.BaseFragmentLifeCycle
import com.pacenow.exam.ui.main.viewModels.MainViewModel
import com.pacenow.exam.ui.main.views.MainView

class MainFragment : BaseFragmentLifeCycle<MainViewModel, MainView>() {

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = MainView(context)

    override fun onViewCreated(contentView: MainView, savedInstanceState: Bundle?) {}

    override fun observerChanges() {}
}
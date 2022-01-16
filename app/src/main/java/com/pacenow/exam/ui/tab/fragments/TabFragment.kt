package com.pacenow.exam.ui.tab.fragments

import android.content.Context
import android.os.Bundle
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.core.base.BaseFragment
import com.pacenow.exam.ui.tab.views.TabView

class TabFragment : BaseFragment<TabView>() {

    companion object {
        const val KEY_TAB_INDEX = "tab_index"
    }

    private val tabIndex: Int
        get() {
            return arguments?.getInt(KEY_TAB_INDEX) ?: -1
        }

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = TabView(context)

    override fun onViewCreated(
        contentView: TabView,
        savedInstanceState: Bundle?
    ) {
        contentView.setUpView(tabIndex)
    }
}
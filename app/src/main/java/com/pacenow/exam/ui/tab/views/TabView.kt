package com.pacenow.exam.ui.tab.views

import android.content.Context
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentView
import kotlinx.android.synthetic.main.view_app_bar.view.*

class TabView(context: Context) :  BaseFragmentView(context) {

    init {
        inflate(context, R.layout.view_tab, this)
    }

    fun setUpView(tabIndex: Int) {
        toolbar.title = "Tab %s".format(tabIndex)
    }
}
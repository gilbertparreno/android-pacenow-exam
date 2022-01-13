package com.pacenow.exam.ui.main.views

import android.content.Context
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentView

class MainView(context: Context) : BaseFragmentView(context) {

    init {
        inflate(context, R.layout.view_main, this)
    }
}
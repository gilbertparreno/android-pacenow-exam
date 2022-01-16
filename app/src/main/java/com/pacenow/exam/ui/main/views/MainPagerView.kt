package com.pacenow.exam.ui.main.views

import android.content.Context
import androidx.fragment.app.Fragment
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentView
import com.pacenow.exam.ui.main.adapters.MainPagerAdapter
import kotlinx.android.synthetic.main.view_main_pager.view.*

class MainPagerView(context: Context) : BaseFragmentView(context) {

    init {
        inflate(context, R.layout.view_main_pager, this)
    }

    fun setUpView(fragment: Fragment) {
        val adapter = MainPagerAdapter(fragment)
        mainViewPager.adapter = adapter
    }
}
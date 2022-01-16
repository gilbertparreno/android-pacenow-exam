package com.pacenow.exam.ui.main.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pacenow.exam.ui.settings.fragments.SettingsFragment
import com.pacenow.exam.ui.tab.fragments.TabFragment

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return fragmentTabs(position)
    }

    private fun fragmentTabs(position: Int): Fragment {
        return if (position < 4) {
            TabFragment().apply {
                arguments = Bundle().apply {
                    putInt(TabFragment.KEY_TAB_INDEX, position.plus(1))
                }
            }
        } else {
            SettingsFragment()
        }
    }
}
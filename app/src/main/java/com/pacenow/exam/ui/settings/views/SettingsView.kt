package com.pacenow.exam.ui.settings.views

import android.content.Context
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BaseFragmentView
import com.pacenow.exam.core.extensions.getString
import com.pacenow.exam.core.helpers.ToastHelper
import com.pacenow.exam.ui.settings.adapters.LanguageSettingsPagingAdapter
import kotlinx.android.synthetic.main.view_app_bar.view.*
import kotlinx.android.synthetic.main.view_settings.view.*

interface SettingsViewDelegate {
    fun onLogoutPressed()
}

class SettingsView(context: Context) : BaseFragmentView(context) {

    var delegate: SettingsViewDelegate? = null

    val adapter = LanguageSettingsPagingAdapter {
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.menu_cancel)
    }

    init {
        inflate(context, R.layout.view_settings, this)
        toolbar.apply {
            title = getString(R.string.settings)
            inflateMenu(R.menu.menu_logout)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_cancel -> {
                        menu.clear()
                        adapter.deselectAllItems()
                        inflateMenu(R.menu.menu_logout)
                    }
                    R.id.menu_done -> {
                        menu.clear()
                        val selectedCountries = adapter.getSelectedItems()
                        if (selectedCountries.isNotEmpty()) {
                            ToastHelper.showSuccessToast(
                                context = context,
                                text = adapter.getSelectedItems()
                            )
                        }
                        adapter.deselectAllItems()
                        inflateMenu(R.menu.menu_logout)
                    }
                    R.id.menu_logout -> delegate?.onLogoutPressed()
                }
                return@setOnMenuItemClickListener true
            }
        }

        countriesList.adapter = adapter
    }

    fun showLoading() = loading.show()

    fun hideLoading() = loading.hide()
}
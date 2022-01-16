package com.pacenow.exam.ui.settings.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.pacenow.exam.PaceNowExamApp
import com.pacenow.exam.core.base.BaseFragmentLifeCycle
import com.pacenow.exam.core.extensions.launch
import com.pacenow.exam.core.network.enums.TaskStatus
import com.pacenow.exam.core.sharedPreferences.UserSharedPreferences
import com.pacenow.exam.ui.authentication.activities.AuthenticationActivity
import com.pacenow.exam.ui.settings.viewModels.SettingsViewModel
import com.pacenow.exam.ui.settings.views.SettingsView
import com.pacenow.exam.ui.settings.views.SettingsViewDelegate
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class SettingsFragment : BaseFragmentLifeCycle<SettingsViewModel, SettingsView>(),
    SettingsViewDelegate {

    @Inject lateinit var userSharedPreferences: UserSharedPreferences

    override fun inject() {
        PaceNowExamApp.appComponent.inject(this)
    }

    override fun onCreateView(
        context: Context,
        savedInstanceState: Bundle?
    ) = SettingsView(context).also {
        it.delegate = this
    }

    override fun onViewCreated(
        contentView: SettingsView,
        savedInstanceState: Bundle?
    ) {
        viewModel.getCountries()
    }

    override fun observerChanges() {
        viewModel.countriesEvent.observe(this) {
            when(it) {
                is TaskStatus.Success -> {
                    contentView.hideLoading()
                    collectCountries()
                }
                is TaskStatus.Loading -> {
                    contentView.showLoading()
                }
                is TaskStatus.Failure -> {
                    contentView.hideLoading()
                    collectCountries()
                }
            }
        }
    }

    // SettingsViewDelegate

    override fun onLogoutPressed() {
        userSharedPreferences.clear()
        val intent = Intent(activity, AuthenticationActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun collectCountries() {
        lifecycleScope.launch {
            viewModel.allCountries.collectLatest { contentView.adapter.submitData(it) }
        }
    }
}
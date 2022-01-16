package com.pacenow.exam.core.di

import com.pacenow.exam.core.network.di.NetworkModule
import com.pacenow.exam.ui.authentication.activities.AuthenticationActivity
import com.pacenow.exam.ui.main.activities.MainActivity
import com.pacenow.exam.core.room.di.RoomModule
import com.pacenow.exam.ui.authentication.fragments.LoginFragment
import com.pacenow.exam.ui.main.fragments.MainPagerFragment
import com.pacenow.exam.ui.settings.fragments.SettingsFragment
import com.pacenow.exam.ui.tab.fragments.TabFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RoomModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(authenticationActivity: AuthenticationActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(mainPagerFragment: MainPagerFragment)
    fun inject(tabFragment: TabFragment)
    fun inject(settingsFragment: SettingsFragment)
}
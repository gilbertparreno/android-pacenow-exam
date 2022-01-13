package com.pacenow.exam.core.di

import com.pacenow.exam.ui.authentication.activities.AuthenticationActivity
import com.pacenow.exam.ui.main.activities.MainActivity
import com.pacenow.exam.core.room.di.RoomModule
import com.pacenow.exam.ui.authentication.fragments.LoginFragment
import com.pacenow.exam.ui.main.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RoomModule::class])
@Singleton
interface AppComponent {
    fun inject(authenticationActivity: AuthenticationActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}
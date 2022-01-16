package com.pacenow.exam.core.sharedPreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserSharedPreferences @Inject constructor(
    @Named(value = "userPreferences") private val sharedPreferences: SharedPreferences
) {

    var userId: Int = sharedPreferences.getInt("user.id", -1)
        set(value) {
            sharedPreferences.edit {
                putInt("user.id", value)
            }
            field = value
        }

    var rememberMe: Boolean =  sharedPreferences.getBoolean("user.remember_me", false)
    set(value) {
        sharedPreferences.edit {
            putBoolean("user.remember_me", value)
        }
        field = value
    }

    fun isLogged() = userId != -1 && rememberMe

    fun clear() {
        userId = -1
        rememberMe = false
    }
}
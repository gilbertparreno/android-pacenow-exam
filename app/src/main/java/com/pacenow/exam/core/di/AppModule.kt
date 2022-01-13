package com.pacenow.exam.core.di

import android.content.Context
import android.content.SharedPreferences
import com.pacenow.exam.core.providers.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    @Named(value = "userPreferences")
    fun providesSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesCoroutineContextProvider() = CoroutineContextProvider()

    @Provides
    @Singleton
    fun providesEventbus() = EventBus.getDefault()
}
package com.pacenow.exam

import android.app.Application
import androidx.startup.AppInitializer
import com.pacenow.exam.core.di.AppComponent
import com.pacenow.exam.core.di.AppModule
import com.pacenow.exam.core.di.DaggerAppComponent
import com.pacenow.exam.core.initializers.room.repositories.RoomUserRepositoryInitializer
import com.pacenow.exam.core.room.di.RoomModule

class PaceNowExamApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val userRepository = AppInitializer.getInstance(this)
            .initializeComponent(RoomUserRepositoryInitializer::class.java)
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(listOf(userRepository))).build()
    }
}
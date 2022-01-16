package com.pacenow.exam

import android.app.Application
import androidx.startup.AppInitializer
import com.pacenow.exam.core.di.AppComponent
import com.pacenow.exam.core.di.AppModule
import com.pacenow.exam.core.di.DaggerAppComponent
import com.pacenow.exam.core.initializers.room.repositories.RoomRepositoriesInitializer
import com.pacenow.exam.core.network.di.NetworkModule
import com.pacenow.exam.core.room.di.RoomModule
import timber.log.Timber

class PaceNowExamApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val repositories = AppInitializer.getInstance(this)
            .initializeComponent(RoomRepositoriesInitializer::class.java)

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(repositories))
            .networkModule(NetworkModule())
            .build()

        Timber.plant(Timber.DebugTree())
    }
}
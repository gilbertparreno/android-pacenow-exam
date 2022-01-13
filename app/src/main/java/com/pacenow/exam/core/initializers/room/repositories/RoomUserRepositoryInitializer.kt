package com.pacenow.exam.core.initializers.room.repositories

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import com.pacenow.exam.core.initializers.room.RoomDatabaseInitializer
import com.pacenow.exam.core.room.daos.UserDao

class RoomUserRepositoryInitializer : Initializer<UserDao> {

    override fun create(context: Context): UserDao {
        val appDatabase = AppInitializer.getInstance(context)
            .initializeComponent(RoomDatabaseInitializer::class.java)
        return appDatabase.userDao()
    }

    override fun dependencies() = listOf(RoomDatabaseInitializer::class.java)
}
package com.pacenow.exam.core.initializers.room.repositories

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import com.pacenow.exam.core.initializers.room.RoomDatabaseInitializer
import com.pacenow.exam.core.room.base.BaseRoomDao

class RoomRepositoriesInitializer : Initializer<List<BaseRoomDao<*>>> {

    override fun create(context: Context): List<BaseRoomDao<*>> {
        val appDatabase = AppInitializer.getInstance(context)
            .initializeComponent(RoomDatabaseInitializer::class.java)
        return listOf(
            appDatabase.userDao(),
            appDatabase.countryDao()
        )
    }

    override fun dependencies() = listOf(RoomDatabaseInitializer::class.java)
}
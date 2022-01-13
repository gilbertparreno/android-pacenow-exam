package com.pacenow.exam.core.initializers.room

import android.content.Context
import androidx.room.Room
import androidx.startup.Initializer
import com.pacenow.exam.core.room.database.AppDatabase

class RoomDatabaseInitializer : Initializer<AppDatabase> {

    override fun create(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}
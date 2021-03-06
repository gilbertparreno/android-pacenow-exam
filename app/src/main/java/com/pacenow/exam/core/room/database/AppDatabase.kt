package com.pacenow.exam.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pacenow.exam.core.room.daos.CountryDao
import com.pacenow.exam.core.room.daos.UserDao
import com.pacenow.exam.core.room.entities.Country
import com.pacenow.exam.core.room.entities.User

@Database(entities = [User::class, Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun countryDao(): CountryDao
}
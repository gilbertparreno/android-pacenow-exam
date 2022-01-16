package com.pacenow.exam.core.room.di

import com.pacenow.exam.core.room.base.BaseRoomDao
import com.pacenow.exam.core.room.daos.CountryDao
import com.pacenow.exam.core.room.daos.UserDao
import com.pacenow.exam.core.room.repositories.CountryRepository
import com.pacenow.exam.core.room.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val repositories: List<BaseRoomDao<*>>) {

    @Provides
    @Singleton
    fun providesUserRepository(): UserRepository {
        val userDao = repositories
            .filterIsInstance(UserDao::class.java)
            .first()
        return UserRepository(userDao)
    }

    @Provides
    @Singleton
    fun providesCountriesRepository(): CountryRepository {
        val countriesDao = repositories
            .filterIsInstance(CountryDao::class.java)
            .first()
        return CountryRepository(countriesDao)
    }
}
package com.pacenow.exam.core.room.repositories

import com.pacenow.exam.core.room.base.BaseRoomRepository
import com.pacenow.exam.core.room.daos.UserDao
import com.pacenow.exam.core.room.entities.User

class UserRepository(
    private val userDao: UserDao
) : BaseRoomRepository<User, UserDao>(userDao) {
    override suspend fun findAll(): List<User> = userDao.findAll()
    override suspend fun find(id: Int) = userDao.find(id)
    suspend fun getCityByName(name: String) = userDao.getCityByName(name)
}
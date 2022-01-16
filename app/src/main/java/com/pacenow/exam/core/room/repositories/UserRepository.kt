package com.pacenow.exam.core.room.repositories

import com.pacenow.exam.core.room.base.BaseRoomRepository
import com.pacenow.exam.core.room.daos.UserDao
import com.pacenow.exam.core.room.entities.User

class UserRepository(
    private val userDao: UserDao
) : BaseRoomRepository<User, UserDao>(userDao) {

    suspend fun findAll() = userDao.findAll()
    suspend fun find(id: Int) = userDao.find(id)
    suspend fun findUser(email: String) = userDao.findUser(email)
}
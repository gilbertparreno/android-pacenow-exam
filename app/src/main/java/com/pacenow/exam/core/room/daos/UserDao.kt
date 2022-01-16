package com.pacenow.exam.core.room.daos

import androidx.room.Dao
import androidx.room.Query
import com.pacenow.exam.core.room.base.BaseRoomDao
import com.pacenow.exam.core.room.entities.User

@Dao
interface UserDao : BaseRoomDao<User> {
    @Query("SELECT * FROM user")
    suspend fun findAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun find(id: Int): User?

    @Query("SELECT * FROM user WHERE user_name == :email")
    suspend fun findUser(email: String): User?
}
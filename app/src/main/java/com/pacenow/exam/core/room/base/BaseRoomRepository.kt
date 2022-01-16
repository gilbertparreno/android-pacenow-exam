package com.pacenow.exam.core.room.base

import com.pacenow.exam.core.room.interfaces.RoomEntity

abstract class BaseRoomRepository<T : RoomEntity, D : BaseRoomDao<T>>(
    private val dao: D
) {
    suspend fun insert(vararg args: T) = dao.insert(*args)
    suspend fun update(vararg args: T) = dao.update(*args)
    suspend fun delete(vararg args: T) = dao.delete(*args)
}
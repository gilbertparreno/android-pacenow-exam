package com.pacenow.exam.core.room.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.pacenow.exam.core.room.entities.interfaces.RoomEntity

interface BaseRoomDao<T : RoomEntity> {
    @Insert
    suspend fun insert(vararg args: T)
    @Update
    suspend fun update(vararg args: T)
    @Delete
    suspend fun delete(vararg args: T)
}
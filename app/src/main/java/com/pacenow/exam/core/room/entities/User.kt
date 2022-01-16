package com.pacenow.exam.core.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pacenow.exam.core.room.interfaces.RoomEntity

@Entity(indices = [Index(value = ["user_name"])])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password_salt") val passwordSalt: String,
    @ColumnInfo(name = "password_iv") val passwordIv: String,
    @ColumnInfo(name = "password_encrypted") val passwordEncrypted: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String
) : RoomEntity
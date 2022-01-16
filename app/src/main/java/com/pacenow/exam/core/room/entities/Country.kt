package com.pacenow.exam.core.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.pacenow.exam.core.room.interfaces.RoomEntity

@Entity(primaryKeys = ["code"])
data class Country(
    @ColumnInfo val code: String,
    @ColumnInfo val country: String,
    @ColumnInfo val region: String
) : RoomEntity
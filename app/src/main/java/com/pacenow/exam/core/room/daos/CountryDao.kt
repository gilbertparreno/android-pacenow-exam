package com.pacenow.exam.core.room.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.pacenow.exam.core.room.base.BaseRoomDao
import com.pacenow.exam.core.room.entities.Country

@Dao
interface CountryDao : BaseRoomDao<Country> {
    @Query("SELECT * FROM country ORDER BY country ASC")
    suspend fun findAll(): List<Country>

    @Query("SELECT count(code) FROM country")
    suspend fun count(): Int

    @Query("SELECT * FROM country ORDER BY country ASC")
    fun getAlLCountries(): PagingSource<Int, Country>

    @Query("DELETE FROM country")
    suspend fun deleteAllRows()
}
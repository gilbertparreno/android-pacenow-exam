package com.pacenow.exam.core.room.repositories

import com.pacenow.exam.core.room.base.BaseRoomRepository
import com.pacenow.exam.core.room.daos.CountryDao
import com.pacenow.exam.core.room.entities.Country

class CountryRepository(
    private val countryDao: CountryDao
) : BaseRoomRepository<Country, CountryDao>(countryDao) {

    suspend fun findAll() = countryDao.findAll()
    suspend fun count() = countryDao.count()
    fun getAllCountries() = countryDao.getAlLCountries()
    suspend fun deleteAllRows() = countryDao.deleteAllRows()
}
package com.pacenow.exam.core.network.repositories

import com.pacenow.exam.core.network.services.AppService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val appService: AppService
) {
    suspend fun getCountries() = appService.getCountries()
}
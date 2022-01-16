package com.pacenow.exam.core.network.services

import com.pacenow.exam.core.network.entities.ApiCountry
import retrofit2.http.GET

interface AppService {
    @GET("countries")
    suspend fun getCountries(): List<ApiCountry>
}
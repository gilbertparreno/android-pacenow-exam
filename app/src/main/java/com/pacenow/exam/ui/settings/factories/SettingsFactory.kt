package com.pacenow.exam.ui.settings.factories

import com.pacenow.exam.core.room.entities.Country
import com.pacenow.exam.ui.settings.entities.CountryItem

object SettingsFactory {

    private const val flagUrl = "https://www.worldatlas.com/r/w425/img/flag/%s-flag.jpg"

    fun getCountryItems(countries: List<Country>): List<CountryItem> {
        return countries.map {
            CountryItem(
                code = it.code,
                flagUrl = flagUrl.format(it.code.lowercase()),
                country = it.country,
                region = it.region
            )
        }
    }

    fun getCountryItem(country: Country) : CountryItem {
        return CountryItem(
            code = country.code,
            flagUrl = flagUrl.format(country.code.lowercase()),
            country = country.country,
            region = country.region
        )
    }
}
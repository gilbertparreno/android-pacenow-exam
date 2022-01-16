package com.pacenow.exam.ui.settings.entities

import com.pacenow.exam.core.extensions.allTrue
import com.pacenow.exam.core.room.entities.Country

data class CountryItem(
    val code: String,
    val flagUrl: String,
    val country: String,
    val region: String,
    var isSelected: Boolean = false
) {

    fun from(country: Country) : CountryItem {
        return CountryItem(
            code = country.code,
            flagUrl = "https://www.worldatlas.com/r/w425/img/flag/%s-flag.jpg".format(country.code.lowercase()),
            country = country.country,
            region = country.region
        )
    }

    override fun equals(other: Any?): Boolean {
        val otherObject = other as? CountryItem ?: return false
        return allTrue(
            otherObject.code == code,
            otherObject.flagUrl == flagUrl,
            otherObject.country == country,
            otherObject.region == region,
            otherObject.isSelected == isSelected
        )
    }

    override fun hashCode() = System.identityHashCode(this)
}
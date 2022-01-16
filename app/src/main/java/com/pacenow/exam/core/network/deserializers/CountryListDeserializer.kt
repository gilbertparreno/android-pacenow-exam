package com.pacenow.exam.core.network.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.pacenow.exam.core.network.entities.ApiCountry
import java.lang.reflect.Type

class CountryListDeserializer : JsonDeserializer<List<ApiCountry>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<ApiCountry> {
        return json?.asJsonObject?.getAsJsonObject("data")?.entrySet()?.mapNotNull {
            val countryDetailsObject = it.value?.asJsonObject ?: return@mapNotNull null
            val country = countryDetailsObject.getAsJsonPrimitive("country")
                ?.asString ?: return@mapNotNull null
            val region = countryDetailsObject.getAsJsonPrimitive("region")
                ?.asString ?: return@mapNotNull null
            ApiCountry(
                code = it.key,
                country = country,
                region = region
            )
        } ?: emptyList()
    }
}
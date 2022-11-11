package info.ccook.geobubble.data.cities

import info.ccook.geobubble.data.cities.models.DataCity
import info.ccook.geobubble.data.cities.network.models.ApiCitySearchResponse
import info.ccook.geobubble.data.cities.network.models.ApiCitySearchResult

/**
 * Map a [ApiCitySearchResponse] to a [List] of [DataCity].
 *
 * @return [List] of [DataCity] or an empty [List].
 */
internal fun ApiCitySearchResponse.toDataModel(): List<DataCity> {
    return this.embedded?.citySearchResults?.map { citySearchResult ->
        citySearchResult.toDataModel()
    } ?: listOf()
}

/**
 * Map a [ApiCitySearchResult] to a [DataCity].
 *
 * @return [DataCity]
 */
internal fun ApiCitySearchResult.toDataModel(): DataCity {
    return DataCity(fullName = this.matchingFullName ?: "")
}

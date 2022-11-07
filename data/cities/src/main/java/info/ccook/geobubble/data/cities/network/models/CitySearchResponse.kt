package info.ccook.geobubble.data.cities.network.models

import com.squareup.moshi.Json

/**
 * The API data returned when searching for a city.
 *
 * @param embedded Embedded data related search results.
 */
internal data class CitySearchResponse(

    @Json(name = "_embedded")
    val embedded: Embedded? = Embedded()
)

internal data class Embedded(

    @Json(name = "city:search-results")
    val citySearchResults: List<CitySearchResult>? = listOf()
)

internal data class CitySearchResult(

    @Json(name = "matching_full_name")
    val matchingFullName: String? = ""
)

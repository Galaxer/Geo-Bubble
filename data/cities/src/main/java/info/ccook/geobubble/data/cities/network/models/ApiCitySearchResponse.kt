package info.ccook.geobubble.data.cities.network.models

import com.squareup.moshi.Json

/**
 * The API data returned when searching for a city.
 *
 * @param embedded Embedded data related search results.
 */
internal data class ApiCitySearchResponse(

    @Json(name = "_embedded")
    val embedded: ApiEmbedded? = ApiEmbedded()
)

internal data class ApiEmbedded(

    @Json(name = "city:search-results")
    val citySearchResults: List<ApiCitySearchResult>? = listOf()
)

internal data class ApiCitySearchResult(

    @Json(name = "matching_full_name")
    val matchingFullName: String? = ""
)

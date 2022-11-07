package info.ccook.geobubble.data.cities

import info.ccook.geobubble.data.cities.models.City
import info.ccook.geobubble.data.cities.network.models.CitySearchResponse

internal interface Mapper {

    fun citiesSearchResponseToCities(citiesSearchResponse: CitySearchResponse): List<City>
}

internal class MapperImpl : Mapper {

    override fun citiesSearchResponseToCities(
        citiesSearchResponse: CitySearchResponse
    ): List<City> {
        return citiesSearchResponse.embedded?.citySearchResults?.map { citySearchResult ->
            City(fullName = citySearchResult.matchingFullName ?: "")
        } ?: listOf()
    }
}

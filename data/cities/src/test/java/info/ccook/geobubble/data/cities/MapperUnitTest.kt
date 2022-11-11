package info.ccook.geobubble.data.cities

import info.ccook.geobubble.data.cities.models.DataCity
import info.ccook.geobubble.data.cities.network.models.ApiCitySearchResponse
import info.ccook.geobubble.data.cities.network.models.ApiCitySearchResult
import info.ccook.geobubble.data.cities.network.models.ApiEmbedded
import org.junit.Test

import org.junit.Assert.*

class MapperUnitTest {

    @Test
    fun mapApiModelToDataModel_isCorrect() {
        val cityFullName = "Raleigh"
        val apiSearchResult = ApiCitySearchResult(matchingFullName = cityFullName)
        val apiEmbedded = ApiEmbedded(citySearchResults = listOf(apiSearchResult))
        val apiSearchResponse = ApiCitySearchResponse(embedded = apiEmbedded)
        val expected = listOf(DataCity(fullName = cityFullName))
        assertEquals(expected, apiSearchResponse.toDataModel())
    }

    @Test
    fun mapApiModelToDataModel_isEmpty() {
        val apiSearchResponse = ApiCitySearchResponse(embedded = null)
        val expected = listOf<DataCity>()
        assertEquals(expected, apiSearchResponse.toDataModel())
    }
}

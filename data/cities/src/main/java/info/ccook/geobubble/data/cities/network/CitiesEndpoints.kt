package info.ccook.geobubble.data.cities.network

import info.ccook.geobubble.data.cities.network.models.ApiCitySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Endpoints for requesting city data.
 */
internal interface CitiesEndpoints {

    /**
     * Search cities by name.
     *
     * @param text full or partial text of the city name to search by.
     * @return [Response] of [ApiCitySearchResponse]
     */
    @GET("cities")
    suspend fun searchCities(
        @Query("search") text: String
    ): Response<ApiCitySearchResponse>
}

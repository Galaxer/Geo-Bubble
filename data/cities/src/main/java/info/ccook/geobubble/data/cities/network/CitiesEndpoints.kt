package info.ccook.geobubble.data.cities.network

import info.ccook.geobubble.data.cities.network.models.CitySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CitiesEndpoints {

    @GET("cities")
    suspend fun searchCities(
        @Query("search") text: String
    ): Response<CitySearchResponse>
}

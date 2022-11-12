package info.ccook.geobubble.data.cities

import info.ccook.geobubble.data.cities.models.DataCity
import info.ccook.geobubble.data.cities.network.CitiesEndpoints
import info.ccook.geobubble.data.cities.network.models.ApiCitySearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

interface CitiesSearchRepository {

    suspend fun searchCities(text: String): Result<List<DataCity>>
}

class CitiesSearchRepositoryImpl @Inject internal constructor(
    private val endpoints: CitiesEndpoints
) : CitiesSearchRepository {

    override suspend fun searchCities(text: String): Result<List<DataCity>> {
        return withContext(Dispatchers.IO) {
            catchException {
                val response = endpoints.searchCities(text)
                if (response.isSuccessful) {
                    val body = getResponseBody(response, ApiCitySearchResponse())
                    Result.success(body.toDataModel())
                } else {
                    Result.failure(HttpException(response))
                }
            }
        }
    }

    private fun <T> getResponseBody(response: Response<T>, defaultValue: T): T {
        return response.body() ?: defaultValue
    }

    private suspend fun <T> catchException(call: suspend () -> Result<T>): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

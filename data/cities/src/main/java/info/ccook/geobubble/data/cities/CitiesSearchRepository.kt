package info.ccook.geobubble.data.cities

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import info.ccook.geobubble.data.cities.models.City
import info.ccook.geobubble.data.cities.network.CitiesEndpoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface CitiesSearchRepository {

    suspend fun searchCities(text: String): List<City>
}

class CitiesSearchRepositoryImpl internal constructor(
    private val endpoints: CitiesEndpoints,
    private val mapper: Mapper
) : CitiesSearchRepository {

    constructor() : this(
        endpoints = citiesEndpoints,
        mapper = MapperImpl()
    )

    override suspend fun searchCities(text: String): List<City> {
        return withContext(Dispatchers.IO) {
            val body = endpoints.searchCities(text).body()!!
            mapper.citiesSearchResponseToCities(body)
        }
    }
}

private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

private val moshiConverterFactory = MoshiConverterFactory.create(moshi)

private object HeaderName {
    const val ACCEPT = "Accept"
}

private object API {
    const val BASE_URL = "https://api.teleport.org/api/"
    const val ACCEPT_HEADER = "application/vnd.teleport.v1+json"
}

private val interceptor = Interceptor { chain ->
    val request = chain
        .request()
        .newBuilder()
        .addHeader(HeaderName.ACCEPT, API.ACCEPT_HEADER)
        .build()
    return@Interceptor chain.proceed(request)
}

private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

private val retrofit = Retrofit.Builder()
    .baseUrl(API.BASE_URL)
    .client(httpClient)
    .addConverterFactory(moshiConverterFactory)
    .build()

private val citiesEndpoints = retrofit.create(CitiesEndpoints::class.java)

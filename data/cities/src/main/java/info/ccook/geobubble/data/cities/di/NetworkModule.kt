package info.ccook.geobubble.data.cities.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.ccook.geobubble.data.cities.CitiesSearchRepository
import info.ccook.geobubble.data.cities.CitiesSearchRepositoryImpl
import info.ccook.geobubble.data.cities.network.CitiesEndpoints
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private object HeaderName {
        const val ACCEPT = "Accept"
    }

    private object API {
        const val BASE_URL = "https://api.teleport.org/api/"
        const val ACCEPT_HEADER = "application/vnd.teleport.v1+json"
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader(HeaderName.ACCEPT, API.ACCEPT_HEADER)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }

    @Provides
    fun provideHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .client(httpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun provideCitiesSearchRepository(retrofit: Retrofit): CitiesSearchRepository {
        val endpoints = retrofit.create(CitiesEndpoints::class.java)
        return CitiesSearchRepositoryImpl(endpoints)
    }
}

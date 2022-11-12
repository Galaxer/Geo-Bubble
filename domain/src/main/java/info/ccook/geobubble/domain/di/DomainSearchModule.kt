package info.ccook.geobubble.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.ccook.geobubble.data.cities.CitiesSearchRepository
import info.ccook.geobubble.data.cities.CitiesSearchRepositoryImpl
import info.ccook.geobubble.data.cities.di.NetworkModule
import info.ccook.geobubble.domain.SearchCitiesUseCase
import info.ccook.geobubble.domain.SearchCitiesUseCaseImpl

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object DomainSearchModule {

    @Provides
    fun provideSearchCitiesUseCase(
        citiesSearchRepository: CitiesSearchRepository
    ): SearchCitiesUseCase {
        return SearchCitiesUseCaseImpl(citiesSearchRepository = citiesSearchRepository)
    }
}

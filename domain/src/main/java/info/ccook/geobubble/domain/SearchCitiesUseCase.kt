package info.ccook.geobubble.domain

import info.ccook.geobubble.data.cities.CitiesSearchRepository
import info.ccook.geobubble.data.cities.models.DataCity
import info.ccook.geobubble.domain.models.DomainCity
import javax.inject.Inject

interface SearchCitiesUseCase {

    suspend operator fun invoke(text: String): Result<List<DomainCity>>
}

class SearchCitiesUseCaseImpl @Inject constructor(
    private val citiesSearchRepository: CitiesSearchRepository
) : SearchCitiesUseCase {

    override suspend fun invoke(text: String): Result<List<DomainCity>> {
        return citiesSearchRepository.searchCities(text)
            .map { dataCities -> dataCities.toDomainModel() }
    }
}

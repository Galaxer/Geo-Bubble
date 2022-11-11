package info.ccook.geobubble.domain

import info.ccook.geobubble.data.cities.CitiesSearchRepository
import info.ccook.geobubble.data.cities.models.DataCity

interface SearchCitiesUseCase {

    suspend operator fun invoke(text: String): Result<List<DataCity>>
}

class SearchCitiesUseCaseImpl(
    private val citiesSearchRepository: CitiesSearchRepository
) : SearchCitiesUseCase {

    override suspend fun invoke(text: String): Result<List<DataCity>> {
        return citiesSearchRepository.searchCities(text)
    }
}

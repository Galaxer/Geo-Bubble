package info.ccook.geobubble.feature.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.ccook.geobubble.domain.SearchCitiesUseCase
import info.ccook.geobubble.domain.models.DomainCity
import info.ccook.geobubble.ui.StateViewModel
import info.ccook.geobubble.ui.models.City
import info.ccook.geobubble.ui.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : StateViewModel<SearchScreenState, SearchScreenResult>(SearchScreenState()) {

    override fun resultToState(
        previousState: SearchScreenState,
        result: SearchScreenResult
    ): SearchScreenState {
        return when (result) {
            is SearchScreenResult.OnSearchSuccess -> {
                previousState.copy(
                    citySearchResults = result.cities,
                    isLoading = false
                )
            }
            SearchScreenResult.OnSearchFailure -> {
                previousState.copy(
                    isError = true,
                    isLoading = false
                )
            }
            is SearchScreenResult.Loading -> {
                previousState.copy(isLoading = result.isLoading)
            }
        }
    }

    fun setLoading(isLoading: Boolean = true) {
        updateState(SearchScreenResult.Loading(isLoading))
    }

    fun searchCities(text: String) {
        setLoading()
        viewModelScope.launch {
            searchCitiesUseCase(text)
                .onSuccess { domainCities ->
                    updateState(SearchScreenResult.OnSearchSuccess(domainCities))
                }
                .onFailure {
                    updateState(SearchScreenResult.OnSearchFailure)
                }
        }
    }
}

data class SearchScreenState(
    val citySearchResults: List<City> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

sealed class SearchScreenResult {

    data class OnSearchSuccess(private val domainCities: List<DomainCity>) : SearchScreenResult() {
        val cities: List<City> = domainCities.toUiModel()
    }

    object OnSearchFailure : SearchScreenResult()

    data class Loading(val isLoading: Boolean = true) : SearchScreenResult()
}

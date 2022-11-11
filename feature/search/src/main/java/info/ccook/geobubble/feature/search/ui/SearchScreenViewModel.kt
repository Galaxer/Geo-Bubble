package info.ccook.geobubble.feature.search.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.ccook.geobubble.data.cities.models.DataCity
import info.ccook.geobubble.domain.SearchCitiesUseCase
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    fun searchCities(text: String = "") {
        viewModelScope.launch {
            searchCitiesUseCase(text)
                .onSuccess {
                    _state.value = _state.value.copy(citySearchResults = it)
                }
        }
    }
}

data class SearchScreenState(
    val citySearchResults: List<DataCity> = listOf()
)
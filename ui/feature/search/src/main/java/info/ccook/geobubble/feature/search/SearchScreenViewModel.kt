package info.ccook.geobubble.feature.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.ccook.geobubble.domain.SearchCitiesUseCase
import info.ccook.geobubble.ui.models.City
import info.ccook.geobubble.ui.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    fun searchCities(text: String = "") {
        viewModelScope.launch {
            searchCitiesUseCase(text)
                .onSuccess {
                    _state.value = _state.value.copy(citySearchResults = it.toUiModel())
                }
        }
    }
}

data class SearchScreenState(
    val citySearchResults: List<City> = listOf()
)
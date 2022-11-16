package info.ccook.geobubble.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import info.ccook.geobubble.ui.models.City
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.ccook.geobubble.domain.SearchCitiesUseCase
import info.ccook.geobubble.domain.SearchCitiesUseCaseImpl
import info.ccook.geobubble.domain.di.DomainSearchModule

@Composable
fun SearchScreenContainer(viewModel: SearchScreenViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    val onSearch = remember {
        { text: String ->
            viewModel.searchCities(text)
        }
    }

    SearchScreen(
        citySearchResults = state.citySearchResults,
        onSearch = onSearch
    )
}

@Composable
private fun SearchScreen(
    citySearchResults: List<City> = listOf(),
    onSearch: (String) -> Unit = {}
) {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) { focusManager.clearFocus() }
    ) {
        SearchTextField(
            focusManager = focusManager,
            onSearch = onSearch
        )

        SearchResults(results = citySearchResults)
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        citySearchResults = listOf(City("Raleigh"))
    )
}

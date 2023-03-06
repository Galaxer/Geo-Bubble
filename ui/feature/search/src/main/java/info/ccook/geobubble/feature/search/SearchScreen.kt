package info.ccook.geobubble.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import info.ccook.geobubble.ui.models.City

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
        onSearch = onSearch,
        isLoading = state.isLoading
    )
}

@Composable
internal fun SearchScreen(
    citySearchResults: List<City> = listOf(),
    onSearch: (String) -> Unit = remember { {} },
    isLoading: Boolean = false
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

    SearchFullScreenLoadingIndicator(isLoading = isLoading)
}

@Preview
@Composable
private fun SearchScreenPreviewOneResult() {
    val searchResults = listOf(City("Raleigh"))
    SearchScreen(
        citySearchResults = searchResults
    )
}

@Preview
@Composable
private fun SearchScreenPreviewMultipleResults() {
    val searchResults = listOf(
        City("Raleigh"),
        City("Richmond"),
        City("Cary")
    )
    SearchScreen(
        citySearchResults = searchResults
    )
}

@Preview
@Composable
private fun SearchScreenPreviewIsLoading() {
    SearchScreen(
        isLoading = true
    )
}

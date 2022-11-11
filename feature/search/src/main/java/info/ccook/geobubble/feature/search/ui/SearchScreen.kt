package info.ccook.geobubble.feature.search.ui

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
import info.ccook.geobubble.data.cities.models.DataCity

@Composable
fun SearchScreen(
    screenState: State<SearchScreenState> = mutableStateOf(SearchScreenState()),
    onSearch: (String) -> Unit = {}
) {
    val state by screenState

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

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = state.citySearchResults) {
                Text(text = it.fullName)
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        screenState = remember {
            mutableStateOf(SearchScreenState(
                citySearchResults = listOf(DataCity("Test"))
            ))
        }
    )
}

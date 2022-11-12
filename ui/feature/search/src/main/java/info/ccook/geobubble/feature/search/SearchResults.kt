package info.ccook.geobubble.feature.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import info.ccook.geobubble.ui.models.City

@Composable
internal fun SearchResults(results: List<City>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items = results) { city ->
            SearchResult(city = city)
        }
    }
}

@Composable
private fun SearchResult(city: City) {
    Text(text = city.fullName)
}

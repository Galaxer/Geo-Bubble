package info.ccook.geobubble.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.ccook.geobubble.ui.models.City

@Composable
internal fun SearchResults(results: List<City>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(items = results) { index, city ->
            SearchResult(city = city)

            if (!isLastSearchResult(index, results.lastIndex)) {
                SearchResultsDivider()
            }
        }
    }
}

@Composable
private fun SearchResult(city: City) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(16.dp)
    ) {
        Text(
            text = city.fullName,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SearchResultsDivider() {
    Divider(
        color = Color.Gray,
        thickness = 1.dp
    )
}

private fun isLastSearchResult(index: Int, lastIndex: Int): Boolean {
    return index == lastIndex
}

package info.ccook.geobubble.feature.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import info.ccook.geobubble.feature.search.R

@Composable
fun SearchScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField() {

    val textState = remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState.value,
        onValueChange = { textState.value = it },
        label =  { SearchTextFieldLabel() },
        singleLine = true,
        leadingIcon = { SearchTextFieldIcon() },
        maxLines = 1
    )
}

@Composable
fun SearchTextFieldLabel() {
    Text(stringResource(id = R.string.feature_search_label))
}

@Composable
fun SearchTextFieldIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search"
    )
}

package info.ccook.geobubble.feature.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import info.ccook.geobubble.ui.feature.search.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchTextField(
    focusManager: FocusManager = LocalFocusManager.current,
    onSearch: (String) -> Unit = {}
) {

    val textState = remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState.value,
        onValueChange = { textState.value = it },
        label =  { SearchTextFieldLabel() },
        singleLine = true,
        leadingIcon = { SearchTextFieldIcon() },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
                onSearch(textState.value)
            }
        )
    )
}

@Composable
private fun SearchTextFieldLabel() {
    Text(text = stringResource(id = R.string.feature_search_label))
}

@Composable
private fun SearchTextFieldIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search"
    )
}

@Preview
@Composable
private fun SearchTextFieldIconPreview() {
    SearchTextFieldIcon()
}

@Preview
@Composable
private fun SearchTextFieldLabelPreview() {
    SearchTextFieldLabel()
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField()
}
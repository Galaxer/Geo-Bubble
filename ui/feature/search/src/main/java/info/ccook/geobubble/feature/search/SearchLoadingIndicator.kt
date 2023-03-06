package info.ccook.geobubble.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import info.ccook.geobubble.ui.feature.search.R

@Composable
fun SearchFullScreenLoadingIndicator(isLoading: Boolean = false) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SearchLoadingIndicator(isLoading = true)
        }
    }
}

@Composable
fun SearchLoadingIndicator(isLoading: Boolean = false) {
    if (isLoading) {
        val contentDescription = stringResource(id = R.string.feature_search_loading_content_desc)
        CircularProgressIndicator(
            modifier = Modifier.semantics {
                this.contentDescription = contentDescription
            }
        )
    }
}

@Preview
@Composable
private fun SearchLoadingIndicatorPreviewIsLoading() {
    SearchLoadingIndicator(isLoading = true)
}

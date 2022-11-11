package info.ccook.geobubble

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import info.ccook.geobubble.data.cities.CitiesSearchRepositoryImpl
import info.ccook.geobubble.domain.SearchCitiesUseCase
import info.ccook.geobubble.domain.SearchCitiesUseCaseImpl
import info.ccook.geobubble.feature.search.ui.SearchScreen
import info.ccook.geobubble.feature.search.ui.SearchScreenViewModel
import info.ccook.geobubble.ui.theme.GeoBubbleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GeoBubbleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel =
                        SearchScreenViewModel(SearchCitiesUseCaseImpl(CitiesSearchRepositoryImpl()))

                    SearchScreen(
                        screenState = viewModel.state,
                        onSearch = { text ->
                            viewModel.searchCities(text)
                        }
                    )
                }
            }
        }
    }
}

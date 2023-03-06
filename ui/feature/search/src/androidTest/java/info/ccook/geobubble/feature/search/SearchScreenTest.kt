package info.ccook.geobubble.feature.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import info.ccook.geobubble.ui.feature.search.R
import info.ccook.geobubble.ui.models.City
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun searchTextField_hasFocus() {
        composeTestRule.setContent {
            SearchScreen()
        }

        val activity = composeTestRule.activity
        val searchTextFieldLabel = activity.getString(R.string.feature_search_label)

        composeTestRule.onNodeWithText(searchTextFieldLabel).assertIsFocused()
    }

    @Test
    fun searchResults_exists() {
        val testCityName1 = "Test City Name 1"
        val testCityName2 = "Test City Name 2"

        val citySearchResults = listOf(
            City(fullName = testCityName1),
            City(fullName = testCityName2)
        )

        composeTestRule.setContent {
            SearchScreen(citySearchResults = citySearchResults)
        }

        composeTestRule.onNodeWithText(testCityName1).assertIsDisplayed()
        composeTestRule.onNodeWithText(testCityName2).assertIsDisplayed()
    }

    @Test
    fun loading_indicator_is_displayed() {
        composeTestRule.setContent {
            SearchScreen(isLoading = true)
        }

        val activity = composeTestRule.activity
        val loadingContentDescription = activity
            .getString(R.string.feature_search_loading_content_desc)

        composeTestRule.onNodeWithContentDescription(loadingContentDescription).assertIsDisplayed()
    }

    @Test
    fun loading_indicator_is_not_displayed() {
        composeTestRule.setContent {
            SearchScreen()
        }

        val activity = composeTestRule.activity
        val loadingContentDescription = activity
            .getString(R.string.feature_search_loading_content_desc)

        composeTestRule.onNodeWithContentDescription(loadingContentDescription).assertDoesNotExist()
    }
}

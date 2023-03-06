package info.ccook.geobubble.feature.search

import info.ccook.geobubble.domain.SearchCitiesUseCase
import info.ccook.geobubble.domain.models.DomainCity
import info.ccook.geobubble.testutils.MainCoroutineRule
import info.ccook.geobubble.testutils.assertState
import info.ccook.geobubble.ui.models.City
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class SearchScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockSearchCitiesUseCase: SearchCitiesUseCase
    private lateinit var viewModel: SearchScreenViewModel

    @Before
    fun setUp() {
        mockSearchCitiesUseCase = Mockito.mock(SearchCitiesUseCase::class.java)
        viewModel = SearchScreenViewModel(mockSearchCitiesUseCase)
    }

    @Test
    fun `use default state as initial state`() {
        assertEquals(SearchScreenState(), viewModel.state.value)
    }

    @Test
    fun `update state when is loading`() {
        viewModel.assertState(
            SearchScreenState(isLoading = false),
            SearchScreenState(isLoading = true)
        ) {
            viewModel.setLoading()
        }
    }

    @Test
    fun `update state when is not loading`() {
        viewModel.assertState(
            SearchScreenState(isLoading = false),
        ) {
            viewModel.setLoading(isLoading = false)
        }
    }

    @Test
    fun `update state on search success`() = runTest {
        val cityName = "Richmond"
        val city = DomainCity(fullName = cityName)

        Mockito.`when`(mockSearchCitiesUseCase(cityName))
            .thenReturn(Result.success(listOf(city)))

        viewModel.assertState(
            SearchScreenState(isLoading = false),
            SearchScreenState(isLoading = true),
            SearchScreenState(citySearchResults = listOf(City(cityName)))
        ) {
            viewModel.searchCities(cityName)
        }
    }
}

package info.ccook.geobubble

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import info.ccook.geobubble.feature.search.SearchScreenContainer

@Composable
fun MainNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.NavHost.Search.route
    ) {

        composable(AppRoute.NavHost.Search.route) {
            SearchScreenContainer()
        }
    }
}

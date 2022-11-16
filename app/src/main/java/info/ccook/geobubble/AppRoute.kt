package info.ccook.geobubble

sealed class AppRoute {

    sealed class NavHost : AppRoute() {

        abstract val route: String

        object Search : NavHost() {
            override val route = "search"
        }

        object Home : NavHost() {
            override val route = "home"
        }
    }
}

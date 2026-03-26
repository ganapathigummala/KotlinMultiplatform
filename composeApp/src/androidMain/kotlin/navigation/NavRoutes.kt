package navigation

sealed class NavRoutes(val route: String) {

    object Home : NavRoutes("home")

    object Settings : NavRoutes("settings")

    object Chat : NavRoutes("chat/{message}/{id}") {

        const val baseRoute = "chat"

        fun createRoute(
            message: String,
            id: Int
        ): String {
            return "chat/$message/$id"
        }
    }

    object Profile : NavRoutes("profile")

    object Menu : NavRoutes("menu/{userJson}") {

        const val baseRoute = "menu"

        fun createRoute(
            userJson: String
        ): String {
            return "menu/$userJson"
        }
    }
}
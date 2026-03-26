package navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("Home")
    object Settings : NavRoutes("Settings")
    object Chat : NavRoutes("Chat")
    object Profile : NavRoutes("Profile")
    object Menu : NavRoutes("Menu/{userJson}")
}
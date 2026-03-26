package navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int? = null
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = NavRoutes.Home.route,
        icon = Icons.Filled.Home
    ),

    BottomNavItem(
        title = "Chat",
        route = NavRoutes.Chat.route,
        icon = Icons.Filled.Notifications,
        badgeCount = 45
    ),
    BottomNavItem(
        title = "Profile",
        route = NavRoutes.Profile.route,
        icon = Icons.Filled.Person
    ),
    BottomNavItem(
            title = "Settings",
    route = NavRoutes.Settings.route,
    icon = Icons.Filled.Settings
    )
)
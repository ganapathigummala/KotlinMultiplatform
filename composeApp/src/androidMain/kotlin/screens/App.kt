package screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.*
import navigation.NavRoutes
import navigation.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(onLogout: () -> Unit) {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        NavRoutes.Home.route,
        NavRoutes.Settings.route,
        NavRoutes.Chat.route,
        NavRoutes.Profile.route
    )

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

                title = {
                    Text(
                        text = "Health Care",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },

                navigationIcon = {

                    if (currentRoute !in bottomBarRoutes) {

                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }

                    }

                },

                actions = {

                    IconButton(
                        onClick = {
                            navController.navigate(NavRoutes.Menu.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }

                }
            )
        },

        bottomBar = {

            if (currentRoute in bottomBarRoutes) {

                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {

                    bottomNavItems.forEach { item ->

                        NavigationBarItem(

                            selected = currentRoute == item.route,

                            onClick = {

                                navController.navigate(item.route) {

                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true

                                }

                            },

                            icon = {

                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )

                            },

                            label = {
                                Text(item.title)
                            },

                            colors = NavigationBarItemDefaults.colors(

                                selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                indicatorColor = MaterialTheme.colorScheme.secondaryContainer,

                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(NavRoutes.Home.route) {
                HomeScreen()
            }

            composable(NavRoutes.Settings.route) {
                SettingsScreen()
            }

            composable(NavRoutes.Chat.route) {
                ChatScreen()
            }

            composable(NavRoutes.Profile.route) {
                ProfileScreen()
            }

            composable(NavRoutes.Menu.route) {

                MenuScreen(
                    logOut = {
                        onLogout()
                    }
                )

            }
        }
    }
}
package screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.*
import navigation.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(isEven: Boolean) {

    val navController = rememberNavController()

    // Persist the selected tab index
    var selectedTab by rememberSaveable { mutableStateOf("home") }

    Scaffold(

        topBar = {
            if (isEven) {
                val currentRoute = selectedTab
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
                        if (selectedTab != "home") {
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
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        },

        bottomBar = {
            if (isEven) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    bottomNavItems.forEach { item ->

                        NavigationBarItem(
                            selected = selectedTab == item.route,

                            onClick = {
                                selectedTab = item.route
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

                            label = { Text(item.title) },

                            colors = NavigationBarItemDefaults.colors(
                                /* SELECTED TAB */
                                selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                indicatorColor = MaterialTheme.colorScheme.secondaryContainer,

                                /* UNSELECTED TAB */
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }

    ) { paddingValues ->

        if (isEven) {

            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ) {

                composable("home") { HomeScreen() }
                composable("settings") { SettingsScreen() }
                composable("chat") { ChatScreen() }
                composable("profile") { ProfileScreen() }

            }

        } else {

            AuthenticationScreen(
                modifier = Modifier.padding(paddingValues)
            )

        }
    }
}
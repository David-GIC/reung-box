package com.daviddev.reungbox.ui.screen.root.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.daviddev.reungbox.ui.screen.root.Screen
import com.daviddev.reungbox.ui.theme.GrayColor
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.SecondaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import kotlin.collections.forEach

@Composable
fun CustomBottomBar(navController: NavHostController, items: List<Screen>) {
    // observe current back stack entry as state
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = SecondaryColor) {
        items.forEach { screen ->
            val selected = currentRoute == screen.route

            // Example: show a badge on the Inbox item
            val showBadge = screen is Screen.Download
            val badgeCount = 3 // replace with real state

            NavigationBarItem(
                icon = {
                    if (showBadge && badgeCount > 0) {
                        // Wrap icon with Badge
                        BadgedBox(badge = { Badge { Text(badgeCount.toString()) } }) {
                            screen.icon()
                        }
                    } else {
                        screen.icon()
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = WhiteColor,
                    unselectedIconColor = GrayColor,
                    selectedTextColor = WhiteColor,
                    unselectedTextColor = GrayColor,
                    indicatorColor = PrimaryColor // Selected item background
                ),
                label = { Text(screen.title) },
                selected = selected,
                onClick = {
                    // navigate with state restoration and singleTop behavior
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        // popUpTo start to avoid building up large backstack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                alwaysShowLabel = true // or false to show only for selected item,
            )
        }
    }
}
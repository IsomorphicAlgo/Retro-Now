package com.retronow.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.retronow.app.ui.home.HomeScreen

/**
 * Navigation routes
 */
object Routes {
    const val HOME = "home"
    const val PLANET_DETAIL = "planet_detail/{planetId}"
    const val CALENDAR = "calendar"
    const val LEARN = "learn"
    const val SETTINGS = "settings"
    
    fun planetDetail(planetId: String) = "planet_detail/$planetId"
}

/**
 * Navigation graph for the app
 */
@Composable
fun RetroNowNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.HOME,
    isDarkTheme: Boolean = false,
    onThemeToggle: (Boolean) -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onMenuClick = {
                    // Menu handled by dropdown
                },
                onPlanetClick = { planetId ->
                    navController.navigate(Routes.planetDetail(planetId))
                },
                onNavigateToCalendar = {
                    navController.navigate(Routes.CALENDAR)
                },
                onNavigateToLearn = {
                    navController.navigate(Routes.LEARN)
                },
                onNavigateToSettings = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }
        
        composable(Routes.PLANET_DETAIL) { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId") ?: ""
            com.retronow.app.ui.planetdetail.PlanetDetailScreen(
                planetId = planetId,
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(Routes.CALENDAR) {
            com.retronow.app.ui.calendar.CalendarScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(Routes.LEARN) {
            com.retronow.app.ui.learn.LearnScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(Routes.SETTINGS) {
            com.retronow.app.ui.settings.SettingsScreen(
                onBackClick = { navController.popBackStack() },
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle
            )
        }
    }
}


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
    startDestination: String = Routes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onMenuClick = {
                    // TODO: Open navigation drawer or menu
                },
                onPlanetClick = { planetId ->
                    navController.navigate(Routes.planetDetail(planetId))
                }
            )
        }
        
        composable(Routes.PLANET_DETAIL) { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId") ?: ""
            // TODO: Implement PlanetDetailScreen in Stage 3
            // PlanetDetailScreen(planetId = planetId)
        }
        
        composable(Routes.CALENDAR) {
            // TODO: Implement CalendarScreen in Stage 4
            // CalendarScreen()
        }
        
        composable(Routes.LEARN) {
            // TODO: Implement LearnScreen in Stage 5
            // LearnScreen()
        }
        
        composable(Routes.SETTINGS) {
            // TODO: Implement SettingsScreen in Stage 5
            // SettingsScreen()
        }
    }
}


package com.example.vkeducation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes {
    const val LIST = "app_list"
    const val CARD = "app_card/{appId}"
    fun card(appId: Int) = "app_card/$appId"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LIST) {

        // Список -> карточка приложения
        composable(Routes.LIST) {
            AppListScreen(
                onAppClick = { app -> navController.navigate(Routes.card(app.id)) }
            )
        }

        // Карточка; клик по приложению в «Витрине» -> список приложений
        composable(
            route = Routes.CARD,
            arguments = listOf(navArgument("appId") { type = NavType.IntType }),
        ) { entry ->
            val appId = entry.arguments?.getInt("appId") ?: return@composable
            AppCardScreen(
                appId = appId,
                onBack = { navController.popBackStack() },
                onShowcaseAppClick = {
                    navController.navigate(Routes.LIST) {
                        popUpTo(Routes.LIST) { inclusive = false }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}
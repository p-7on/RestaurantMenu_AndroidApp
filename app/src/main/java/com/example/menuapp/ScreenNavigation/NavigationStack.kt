package com.example.menuapp.ScreenNavigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.menuapp.Model.ItemDetails
import com.example.menuapp.Model.OfferedBurger
import com.example.menuapp.View.DetailsView
import com.example.menuapp.View.OverviewView

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Overview.route) {
        composable(route = Screen.Overview.route) {
            OverviewView(navController = navController)
        }
        composable(route = Screen.Detail.route) { backStackEntry ->
            val item = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<ItemDetails>("item")
            if (item != null) {
                DetailsView(item = item, navigateBack = { navController.popBackStack() })
            }
        }
    }
}
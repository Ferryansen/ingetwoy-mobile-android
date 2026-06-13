package com.gabutmen.ingetwoy.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gabutmen.ingetwoy.navigation.Routes
import com.gabutmen.ingetwoy.ui.screens.add.AddFormScreen
import com.gabutmen.ingetwoy.ui.screens.detail.DetailProductScreen
import com.gabutmen.ingetwoy.ui.screens.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.ADD_FORM) {
            AddFormScreen(navController)
        }
        composable(Routes.DETAIL_PRODUCT) {
            DetailProductScreen(navController)
        }
    }
}

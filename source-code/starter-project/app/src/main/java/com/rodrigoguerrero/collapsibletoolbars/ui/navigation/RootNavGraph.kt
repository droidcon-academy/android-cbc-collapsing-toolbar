package com.rodrigoguerrero.collapsibletoolbars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigoguerrero.collapsibletoolbars.ui.screens.MainScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = "main",
    ) {
        composable(route = "main") {
            MainScreen()
        }
    }
}

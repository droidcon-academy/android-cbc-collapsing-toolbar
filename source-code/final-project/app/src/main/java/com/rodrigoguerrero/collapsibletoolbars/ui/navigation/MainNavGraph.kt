package com.rodrigoguerrero.collapsibletoolbars.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rodrigoguerrero.collapsibletoolbars.data.topArtists
import com.rodrigoguerrero.collapsibletoolbars.ui.screens.ArtistDetailsScreen
import com.rodrigoguerrero.collapsibletoolbars.ui.screens.TopArtistsScreen
import com.rodrigoguerrero.collapsibletoolbars.ui.screens.TopTracksScreen

@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "top-songs"
    ) {
        composable(route = "top-songs") {
            TopTracksScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(route = "top-artists") {
            TopArtistsScreen(
                modifier = Modifier.padding(paddingValues),
                onArtistSelected = { navController.navigate("artist/$it") }
            )
        }
        composable(
            route = "artist/{id}",
            arguments = listOf(
                navArgument(name = "id") { type = NavType.IntType },
            ),
        ) { backStackEntry ->
            val artist = topArtists.find { it.id == backStackEntry.arguments?.getInt("id") }
            artist?.let {
                ArtistDetailsScreen(artist = artist, onBack = { navController.popBackStack() })
            }
        }
    }
}

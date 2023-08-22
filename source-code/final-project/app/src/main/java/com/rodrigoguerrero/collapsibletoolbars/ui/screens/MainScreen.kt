package com.rodrigoguerrero.collapsibletoolbars.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.collapsibletoolbars.ui.navigation.MainNavGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { navController.navigate("top-songs") }) {
                        Icon(imageVector = Icons.Filled.MusicNote, contentDescription = null)
                    }
                    IconButton(
                        onClick = { navController.navigate("top-artists") },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Group,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->
        MainNavGraph(navController = navController, paddingValues = padding)
    }
}

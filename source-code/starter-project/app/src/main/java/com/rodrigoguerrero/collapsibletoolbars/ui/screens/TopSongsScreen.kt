package com.rodrigoguerrero.collapsibletoolbars.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTracksScreen(
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun PreviewTopArtistsScreen() {
    CollapsibleToolbarsTheme {
        TopTracksScreen()
    }
}

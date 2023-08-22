package com.rodrigoguerrero.collapsibletoolbars.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

@Composable
fun TopArtistsScreen(
    onArtistSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun PreviewTopArtistsScreen() {
    CollapsibleToolbarsTheme {
        TopArtistsScreen(onArtistSelected = { })
    }
}

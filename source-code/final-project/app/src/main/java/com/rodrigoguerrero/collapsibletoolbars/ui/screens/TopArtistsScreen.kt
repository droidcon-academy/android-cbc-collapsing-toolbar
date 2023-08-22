package com.rodrigoguerrero.collapsibletoolbars.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.collapsibletoolbars.R
import com.rodrigoguerrero.collapsibletoolbars.data.topArtists
import com.rodrigoguerrero.collapsibletoolbars.ui.components.ArtistItem
import com.rodrigoguerrero.collapsibletoolbars.ui.components.BoxCollapsibleToolbar
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

@Composable
fun TopArtistsScreen(
    onArtistSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxCollapsibleToolbar(
        modifier = modifier,
        title = stringResource(R.string.artists),
        items = {
            items(topArtists) { artist ->
                ArtistItem(artist = artist, onArtistSelected = onArtistSelected)
                Divider(modifier = Modifier.fillMaxWidth())
            }
        },
    )
}

@Preview
@Composable
private fun PreviewTopArtistsScreen() {
    CollapsibleToolbarsTheme {
        TopArtistsScreen(onArtistSelected = { })
    }
}

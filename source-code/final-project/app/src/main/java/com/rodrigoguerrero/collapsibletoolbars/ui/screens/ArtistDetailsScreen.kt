package com.rodrigoguerrero.collapsibletoolbars.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.collapsibletoolbars.data.Artist
import com.rodrigoguerrero.collapsibletoolbars.ui.components.NestedScrollConnectionToolbar

@Composable
fun ArtistDetailsScreen(
    artist: Artist,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NestedScrollConnectionToolbar(
        modifier = modifier,
        content = {
            Text(text = artist.biography, modifier = Modifier.padding(all = 16.dp))
        },
        onBack = onBack,
        title = artist.name,
        imageUrl = artist.imageUrl,
    )
}

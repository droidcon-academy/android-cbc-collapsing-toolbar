package com.rodrigoguerrero.collapsibletoolbars.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoguerrero.collapsibletoolbars.data.Artist
import com.rodrigoguerrero.collapsibletoolbars.data.topArtists
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

@Composable
fun ArtistItem(
    artist: Artist,
    onArtistSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArtistSelected(artist.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artist.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = artist.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp),
        )
        Text(text = artist.name, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview
@Composable
private fun PreviewArtistItem() {
    CollapsibleToolbarsTheme {
        ArtistItem(artist = topArtists[0], onArtistSelected = { })
    }
}

package com.rodrigoguerrero.collapsibletoolbars.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoguerrero.collapsibletoolbars.data.Track
import com.rodrigoguerrero.collapsibletoolbars.data.topTracks
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

@Composable
fun TrackItem(
    track: Track,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(track.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = track.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp),
        )
        Column {
            Text(text = track.name, style = MaterialTheme.typography.titleMedium)
            Text(text = track.artist, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Preview
@Composable
private fun PreviewArtistItem() {
    CollapsibleToolbarsTheme {
        TrackItem(track = topTracks[0])
    }
}

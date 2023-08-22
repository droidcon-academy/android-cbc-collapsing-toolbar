package com.rodrigoguerrero.collapsibletoolbars.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.collapsibletoolbars.R
import com.rodrigoguerrero.collapsibletoolbars.data.topTracks
import com.rodrigoguerrero.collapsibletoolbars.ui.components.TrackItem
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTracksScreen(
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = stringResource(R.string.top_songs)) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
        ) {
            items(topTracks) { track ->
                TrackItem(track = track)
                Divider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTopArtistsScreen() {
    CollapsibleToolbarsTheme {
        TopTracksScreen()
    }
}

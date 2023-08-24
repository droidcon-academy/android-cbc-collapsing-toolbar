package com.rodrigoguerrero.collapsibletoolbars.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.math.abs
import kotlin.math.roundToInt

private val expandedToolbarHeight = 278.dp
private val transitionOffset = 200.dp

@Composable
fun NestedScrollConnectionToolbar(
    title: String,
    imageUrl: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberLazyListState()
    val toolbarHeightPx = with(LocalDensity.current) { expandedToolbarHeight.roundToPx().toFloat() }
    val transitionPx = with(LocalDensity.current) { transitionOffset.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val alphaExpandedTopAppBar = animateFloatAsState(
        targetValue = if (abs(toolbarOffsetHeightPx.floatValue) < transitionPx) {
            1.0f
        } else {
            0.0f
        },
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        ), label = "anim"
    )

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val heightDelta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + heightDelta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = expandedToolbarHeight),
            state = scrollState,
        ) {
            item { content() }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            CollapsedToolbar(
                alphaExpanded = alphaExpandedTopAppBar.value,
                title = title,
                onBack = onBack,
            )
            ExpandedToolbar(
                alphaExpanded = alphaExpandedTopAppBar.value,
                toolbarOffsetHeightPx = toolbarOffsetHeightPx.floatValue.roundToInt(),
                imageUrl = imageUrl,
                title = title,
                onBack = onBack,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CollapsedToolbar(
    alphaExpanded: Float,
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .statusBarsPadding()
            .alpha(abs(1 - alphaExpanded)),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
private fun BoxScope.ExpandedToolbar(
    alphaExpanded: Float,
    toolbarOffsetHeightPx: Int,
    imageUrl: String,
    title: String,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .alpha(alphaExpanded)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx) }
            .height(expandedToolbarHeight)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(alignment = Alignment.Center),
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(Alignment.BottomStart)
        )
    }
    IconButton(
        onClick = onBack,
        modifier = Modifier
            .alpha(alphaExpanded)
            .padding(top = 8.dp, start = 4.dp)
            .align(Alignment.TopStart)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null
        )
    }
}

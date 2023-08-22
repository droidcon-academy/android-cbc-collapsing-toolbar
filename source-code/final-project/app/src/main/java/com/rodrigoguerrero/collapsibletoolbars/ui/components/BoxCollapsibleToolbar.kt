package com.rodrigoguerrero.collapsibletoolbars.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

private val expandedTopAppBarHeight = 152.dp
private val collapsedTopAppBarHeight = 64.dp
private const val FADE_ANIM_DURATION = 100

@Composable
fun BoxCollapsibleToolbar(
    title: String,
    items: LazyListScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    val localDensity = LocalDensity.current
    val state = rememberLazyListState()
    val isCollapsed by remember {
        val deltaHeight = with(localDensity) {
            expandedTopAppBarHeight.toPx() - collapsedTopAppBarHeight.toPx()
        }
        derivedStateOf {
            state.firstVisibleItemScrollOffset > deltaHeight || state.firstVisibleItemIndex > 0
        }
    }
    Box(modifier = modifier) {
        CollapsedTopAppBar(
            title = title,
            isCollapsed = isCollapsed,
            modifier = Modifier.zIndex(10f),
        )
        LazyColumn(state = state) {
            item { ExpandedTopAppBar(title = title) }
            items()
        }
    }
}

@Composable
private fun CollapsedTopAppBar(
    isCollapsed: Boolean,
    title: String,
    modifier: Modifier = Modifier,
) {
    val color: Color by animateColorAsState(
        targetValue = if (isCollapsed) {
            MaterialTheme.colorScheme.primary
        } else {
            Color.Transparent
        },
        label = "color-animation"
    )
    Row(
        modifier = modifier
            .background(color = color)
            .fillMaxWidth()
            .height(collapsedTopAppBarHeight),
    ) {
        AnimatedVisibility(
            visible = isCollapsed,
            enter = fadeIn(animationSpec = tween(FADE_ANIM_DURATION)),
            exit = fadeOut(animationSpec = tween(FADE_ANIM_DURATION))
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun ExpandedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .height(expandedTopAppBarHeight),
        contentAlignment = Alignment.BottomStart,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(18.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun PreviewCollapsedTopAppBar() {
    CollapsibleToolbarsTheme {
        CollapsedTopAppBar(title = "Collapsed Top App Bar", isCollapsed = true)
    }
}

@Preview
@Composable
private fun PreviewExpandedTopAppBar() {
    CollapsibleToolbarsTheme {
        ExpandedTopAppBar(title = "Expanded Top App Bar")
    }
}

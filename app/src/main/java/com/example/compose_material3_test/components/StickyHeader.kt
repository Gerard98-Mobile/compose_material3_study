package com.example.compose_material3_test.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val STICKY_HEADER_ALPHA = 0.8f

@Composable
fun StickyHeader(content: @Composable () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.background.copy(alpha = STICKY_HEADER_ALPHA)
    ) {
        Column(Modifier.padding(8.dp)) {
            content()
        }
    }
}

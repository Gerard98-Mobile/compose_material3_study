package com.example.compose_material3_test.ui.theme

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.compose_material3_test.R
import com.example.compose_material3_test.components.image.ResIcon

@Composable
fun BackIcon(onClick: () -> Unit) = IconButton(onClick = onClick) {
    ResIcon(id = R.drawable.ic_back)
}



package com.example.compose_material3_test.components.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ResIcon(@DrawableRes id: Int, contentDescription: String? = null) =
    Icon(
        painter = painterResource(id = id),
        contentDescription = contentDescription,
    )
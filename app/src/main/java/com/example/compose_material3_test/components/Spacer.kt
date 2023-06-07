package com.example.compose_material3_test.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerW(dp: Dp) = Spacer(modifier = Modifier.width(dp))

@Composable
fun SpacerH(dp: Dp) = Spacer(modifier = Modifier.height(dp))